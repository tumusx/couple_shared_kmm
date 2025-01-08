package com.github.tumusx.savelistapps.network.impl

import com.github.tumusx.network.model.CustomRequest
import com.github.tumusx.network.model.Method
import com.github.tumusx.network.request.CoupleSharedRequestHTTP
import com.github.tumusx.savelistapps.network.contract.IRequester
import com.github.tumusx.savelistapps.network.model.Cats
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.serializer


internal class Requester(private val sharedRequestHTTP: CoupleSharedRequestHTTP) : IRequester {

    override suspend fun onGetSeries(): String {

        //TODO: remove url before doing the api test

        val customRequest = CustomRequest(
            url = "https://api.thecatapi.com/v1/images/search?limit=10",
            method = Method.Get,
            body = null,
            resultType = ListSerializer(serializer<Cats>())
        )

        sharedRequestHTTP.onCustomRequest(customRequest)

        //TODO: remove mock return after completing the api test
        return ""
    }

    override suspend fun onGetMovies(): String {
        TODO("Not yet implemented")
    }
}