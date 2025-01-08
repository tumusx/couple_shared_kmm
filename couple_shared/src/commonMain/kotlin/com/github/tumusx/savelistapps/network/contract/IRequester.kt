package com.github.tumusx.savelistapps.network.contract

interface IRequester {
    suspend fun onGetMovies(): String
    suspend fun onGetSeries(): String
}