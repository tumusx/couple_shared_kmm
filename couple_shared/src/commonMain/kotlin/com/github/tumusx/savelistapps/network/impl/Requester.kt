package com.github.tumusx.savelistapps.network.impl

import com.github.tumusx.network.model.CustomRequest
import com.github.tumusx.network.model.Method
import com.github.tumusx.network.request.CoupleSharedRequestHTTP
import com.github.tumusx.savelistapps.network.contract.IRequester
import com.github.tumusx.savelistapps.network.model.Cats
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.serializer


class Requester(private val sharedRequestHttp: CoupleSharedRequestHTTP) :
    IRequester {

    override suspend fun onListContent(): String {

        //TODO: remove url before doing the api test
        val customRequest = CustomRequest(
            url = "http://api.thecatapi.com/v1/images/search?limit=10",
            method = Method.Get,
            body = null,
            resultType = ListSerializer(serializer<Cats>())
        )

        sharedRequestHttp.onCustomRequest(customRequest)

        //TODO: remove mock after completing the api test
        return ""
    }
}