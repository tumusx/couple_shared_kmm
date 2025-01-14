package com.github.tumusx.savelistapps.network.contract

interface IRequester {
    suspend fun onListContent(): String
}