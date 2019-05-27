package org.jetbrains.kotlinconf.data

import kotlinx.serialization.*

@Serializable
data class LinkData(
    val linkType: String,
    val title: String,
    val url: String
)
