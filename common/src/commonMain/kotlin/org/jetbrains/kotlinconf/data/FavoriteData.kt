package org.jetbrains.kotlinconf.data

import kotlinx.serialization.*

@Serializable
data class FavoriteData(
    var sessionId: String
)