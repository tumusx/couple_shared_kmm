package com.github.tumusx.network.model

import io.ktor.http.HttpMethod
import kotlinx.serialization.KSerializer

data class CustomRequest<T>(
    val url: String,
    val method: Method,
    val headers: HashMap<String, String> = hashMapOf(),
    val body: Any? = null,
    val resultType: KSerializer<T>
)


enum class Method {
    Get, Post, Put, Delete;

    companion object {
        fun onGetRequestMethod(method: Method): HttpMethod {
            return HttpMethod.parse(method.name)
        }
    }
}