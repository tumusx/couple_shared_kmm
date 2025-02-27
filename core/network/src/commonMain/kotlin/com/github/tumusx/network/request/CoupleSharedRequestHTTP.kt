package com.github.tumusx.network.request

import com.github.tumusx.network.client.CoupleSharedClient
import com.github.tumusx.network.model.ApiResult
import com.github.tumusx.network.model.CustomRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.logging.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

interface CoupleSharedRequestHTTP {

    val requestResult: Flow<ApiResult>

    suspend fun <T> onCustomRequest(request: CustomRequest<T>)
}

class CoupleSharedRequestHttpImpl(client: CoupleSharedClient) : CoupleSharedRequestHTTP {
    companion object {
        const val NAME = "CoupleSharedRequestHttpImpl"
    }

    private val client = client.client

    private val _resultRequest: MutableStateFlow<ApiResult> = MutableStateFlow(ApiResult.Loading)

    override val requestResult: Flow<ApiResult> = _resultRequest.asStateFlow()

    override suspend fun <T> onCustomRequest(request: CustomRequest<T>) {

        val httpRequestBuilder = HttpRequestBuilder()
        httpRequestBuilder.url(request.url)
        httpRequestBuilder.setBody(request.body)
        httpRequestBuilder.headers { request.headers }
        httpRequestBuilder.contentType(ContentType.parse("application/json"))
        val resultType = request.resultType

        val result = executeHttpRequest({
            client.request(httpRequestBuilder)
        }, resultType)
        _resultRequest.value = result
    }

    private fun <T> String.decodeJson(serializer: KSerializer<T>?): T? {
        val json = Json { ignoreUnknownKeys = true }
        return serializer?.let { json.decodeFromString(it, this) }
    }

    private suspend fun <T : Any?> executeHttpRequest(
        request: suspend () -> HttpResponse,
        serializer: KSerializer<T>? = null,
    ): ApiResult {
        return try {
            val response = request()
            contentProcess(response, serializer)
        } catch (exception: Exception) {
            exception.printStackTrace()
            ApiResult.UnknownError
        }
    }

    private suspend fun <T> contentProcess(
        response: HttpResponse,
        serializer: KSerializer<T>?
    ): ApiResult {
        val result = response.status.isSuccess()
        return if (result) {
            ApiResult.Success(response.bodyAsText().decodeJson(serializer))
        } else {
            ApiResult.UnknownError
        }
    }
}



