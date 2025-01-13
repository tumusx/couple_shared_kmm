package com.github.tumusx.savelistapps.network.impl

import com.github.tumusx.savelistapps.network.contract.IRequester

actual fun requester(): IRequester {
    return Requester(sharedRequestHTTP)
}