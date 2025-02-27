package com.github.tumusx.network

import com.github.tumusx.network.client.CoupleSharedClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

class CoupleSharedClientAndroidImpl(private val timeoutConfig: Long = 18000) : CoupleSharedClient {

    override val client: HttpClient = HttpClient(OkHttp) {

        install(HttpTimeout) {
            requestTimeoutMillis = timeoutConfig
            socketTimeoutMillis = timeoutConfig
            connectTimeoutMillis = timeoutConfig
        }

        install(ContentNegotiation) {
            json()
        }
    }
}