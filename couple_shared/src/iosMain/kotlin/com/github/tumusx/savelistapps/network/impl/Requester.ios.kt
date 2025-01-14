package com.github.tumusx.savelistapps.network.impl

import com.github.tumusx.savelistapps.network.contract.IRequester

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class CustomRequester{
    actual fun requester(): IRequester {
        return Requester(sharedRequestHTTP)
    }
}