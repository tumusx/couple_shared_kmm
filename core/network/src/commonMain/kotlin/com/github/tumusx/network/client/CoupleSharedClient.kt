package com.github.tumusx.network.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout

interface CoupleSharedClient {

    val client: HttpClient
}

class CoupleSharedClientImpl(private val timeoutConfig: Long = 18000) : CoupleSharedClient {

    override val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }

        install(HttpTimeout) {
            requestTimeoutMillis = timeoutConfig
            socketTimeoutMillis = timeoutConfig
            connectTimeoutMillis = timeoutConfig
        }
    }
}