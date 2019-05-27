package org.jetbrains.kotlinconf.presentation

import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.data.*

interface DataRepository {
    val sessions: List<Session>?
    val favorites: List<Session>?
    val votes: List<VoteData>?
    var onRefreshListeners: List<() -> Unit>
    var privacyPolicyAccepted: Boolean
    var userId: String?

    suspend fun update()
    fun getRating(sessionId: String): VoteData?
    suspend fun addRating(sessionId: String, rating: VoteData)
    suspend fun removeRating(sessionId: String)
    suspend fun setFavorite(sessionId: String, isFavorite: Boolean)
    fun acceptPrivacyPolicy()
}
