package com.github.tumusx.savelistapps.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class Cats(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)