package com.github.tumusx.network.request

import com.github.tumusx.network.client.CoupleSharedClient
import com.github.tumusx.network.model.ApiResult
import com.github.tumusx.network.model.CustomRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface CoupleSharedRequestHTTP {

    val requestResult: Flow<ApiResult>

    suspend fun <T> onCustomRequest(request: CustomRequest<T>)
}

class CoupleSharedRequestHttpImpl(client: CoupleSharedClient) : CoupleSharedRequestHTTP {

    private val client = client.client

    private val _resultRequest = MutableStateFlow(ApiResult.Loading)

    override val requestResult: Flow<ApiResult> = _resultRequest.asStateFlow()

    override suspend fun <T> onCustomRequest(request: CustomRequest<T>) {
        val httpRequestBuilder = HttpRequestBuilder()
        httpRequestBuilder.url { request.url }
        httpRequestBuilder.setBody(request.body)
        httpRequestBuilder.headers { request.headers }

        val resultType = request.resultType

        val result = client.request(httpRequestBuilder)
    }

    private fun <T> decodeJson(json: String): T {

    }

    private fun <T> processInformation(response: HttpResponse): ApiResult {
        val result = response.status.isSuccess()
        if (result) {
            ApiResult.Success(response.bodyAsText())
        } else {
            ApiResult.UnknownError
        }
    }
}



