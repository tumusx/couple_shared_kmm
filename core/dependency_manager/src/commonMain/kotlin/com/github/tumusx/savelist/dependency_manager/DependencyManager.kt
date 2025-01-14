package com.github.tumusx.savelist.dependency_manager

import kotlin.jvm.JvmStatic

object DependencyManager {

    private val dependencies = mutableMapOf<String, Any>()

    fun <T : Any> onRegisterDependency(value: T, key: String): T {

        val dependency = onGetDependency<T>(key)

        if (dependency != null) {
            return dependency
        }

        dependencies[key] = value
        return value
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> onGetDependency(key: String): T? {
        return dependencies[key] as T?
    }

    fun clearAllDependencies() {
        dependencies.clear()
    }

    fun clearSpecificDependency(key: String) {
        dependencies.keys.remove(key)
    }
}