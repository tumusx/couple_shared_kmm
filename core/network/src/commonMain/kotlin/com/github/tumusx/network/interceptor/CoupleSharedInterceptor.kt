package com.github.tumusx.network.interceptor

interface CoupleSharedInterceptor {

    suspend fun onInterceptorRequest()

}


class CoupleSharedInterceptorImpl : CoupleSharedInterceptor {
    override suspend fun onInterceptorRequest() {
        TODO("Not yet implemented")
    }
}