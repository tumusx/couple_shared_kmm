package com.github.tumusx.network.model

sealed interface ApiResult {
    data object UnknownError : ApiResult
    data object NetworkError : ApiResult
    data class Success<T>(val content: T) : ApiResult
    data object Loading : ApiResult
}