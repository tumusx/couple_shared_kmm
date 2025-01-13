package com.github.tumusx.savelist.dependency_manager

import kotlin.jvm.JvmStatic

object DependencyManager {

    private val dependencies = mutableMapOf<String, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> onRegisterDependency(value: T, key: String): T {
        if (onGetDependency<T>(key) != null) {
            return dependencies[key] as T
        }
        dependencies[key] = value
        return dependencies[key] as T
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> onGetDependency(key: String): T? {
        return dependencies[key] as? T
    }

    fun clearAllDependencies() {
        dependencies.clear()
    }

    fun clearSpecificDependency(key: String) {
        dependencies.keys.remove(key)
    }
}