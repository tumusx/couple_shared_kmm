package com.github.tumusx.savelistapps.network.impl

import com.github.tumusx.network.CoupleSharedClientIosImpl
import com.github.tumusx.network.request.CoupleSharedRequestHTTP
import com.github.tumusx.network.request.CoupleSharedRequestHttpImpl
import com.github.tumusx.savelist.dependency_manager.DependencyManager


val sharedRequestHTTP: CoupleSharedRequestHTTP = DependencyManager.onRegisterDependency(
    CoupleSharedRequestHttpImpl(CoupleSharedClientIosImpl()),
    CoupleSharedRequestHttpImpl.NAME
)