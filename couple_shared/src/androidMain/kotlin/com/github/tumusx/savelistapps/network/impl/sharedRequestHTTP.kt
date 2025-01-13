package com.github.tumusx.savelistapps.network.impl

import com.github.tumusx.network.CoupleSharedClientAndroidImpl
import com.github.tumusx.network.request.CoupleSharedRequestHTTP
import com.github.tumusx.network.request.CoupleSharedRequestHttpImpl
import com.github.tumusx.savelist.dependency_manager.DependencyManager


internal val sharedRequestHTTP: CoupleSharedRequestHTTP = DependencyManager.onRegisterDependency(
    CoupleSharedRequestHttpImpl(CoupleSharedClientAndroidImpl()),
    CoupleSharedRequestHttpImpl.NAME
)