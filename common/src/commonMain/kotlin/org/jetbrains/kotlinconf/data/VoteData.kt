package org.jetbrains.kotlinconf.data

import kotlinx.serialization.Serializable

@Serializable
class VoteData(val sessionId: String, val rating: RatingData?)
