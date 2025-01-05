package com.github.tumusx.network.client

import io.ktor.client.HttpClient

interface CoupleSharedClient {

    val client: HttpClient

    fun configClient()
}

class CoupleSharedClientImpl : CoupleSharedClient {
    override val client: HttpClient
        get() = TODO("Not yet implemented")

    override fun configClient() {
        TODO("Not yet implemented")
    }
}