package org.jetbrains.kotlinconf.data

import kotlinx.serialization.*

@Serializable
data class CategoryItemData(
    val name: String,
    val id: Int,
    val sort: Int
)
