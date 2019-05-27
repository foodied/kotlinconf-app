package org.jetbrains.kotlinconf.model

import io.ktor.client.features.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.api.*
import org.jetbrains.kotlinconf.data.*
import org.jetbrains.kotlinconf.presentation.*
import org.jetbrains.kotlinconf.storage.*
import kotlin.properties.*
import kotlin.properties.Delegates.observable

class KotlinConfDataRepository(
    endPoint: String,
    uid: String,
    private val settings: Settings
) : DataRepository {
    private val api = KotlinConfApi(endPoint, uid)

    override var sessions: List<Session>? by bindToPreferencesByKey("settingsKey", Session.serializer().list)
    override var favorites: List<Session>? by bindToPreferencesByKey(
        "favoritesKey",
        Session.serializer().list
    )
    override var votes: List<VoteData>? by bindToPreferencesByKey("votesKey", VoteData.serializer().list)
    override var userId: String? by bindToPreferencesByKey("userIdKey", String.serializer())

    override var privacyPolicyAccepted: Boolean
        get() = settings.getBoolean("privacyPolicyAcceptedKey", false)
        set(value) {
            settings.putBoolean("privacyPolicyAcceptedKey", value)
        }

    private var loggedIn: Boolean = false

    init {
        if (userId == null) userId = uid
    }

    override var onRefreshListeners: List<() -> Unit> = emptyList()

    override suspend fun update() {
        val state = try {
            if (!loggedIn) {
                api.createUser(userId!!)
                loggedIn = true
            }
            api.getAll(userId)
        } catch (cause: Throwable) {
            throw UpdateProblem()
        }

        val newSessions = state.allSessions()
        val newFavorites = state.favoriteSessions()
        val newVotes = state.votes
        if (newSessions != sessions || newFavorites != favorites || newVotes != votes) {
            sessions = newSessions
            favorites = newFavorites
            votes = newVotes
            callRefreshListeners()
        }
    }

    override fun acceptPrivacyPolicy() {
        privacyPolicyAccepted = true
    }

    override fun getRating(sessionId: String): VoteData? =
        votes?.find { sessionId == it.sessionId }
            ?.rating
            ?.let { VoteData.valueOf(it) }

    override suspend fun addRating(sessionId: String, rating: VoteData) {
        val userId = userId ?: throw Unauthorized()
        val vote = VoteData(sessionId, rating.value)
        try {
            api.postVote(vote, userId)
            votes = votes.orEmpty().filter { it.sessionId != sessionId }.plus(vote)
        } catch (apiError: ResponseException) {
            val code = apiError.response.status
            throw when (code.value) {
                477 -> TooEarlyVote()
                478 -> TooLateVote()
                else -> CannotPostVote()
            }
        } catch (_: Throwable) {
            throw CannotPostVote()
        } finally {
            callRefreshListeners()
        }
    }

    override suspend fun removeRating(sessionId: String) {
        val userId = userId ?: throw Unauthorized()
        try {
            api.deleteVote(VoteData(sessionId, 0), userId)
            votes = votes?.filter { it.sessionId != sessionId }
        } catch (e: Throwable) {
            throw CannotDeleteVote()
        } finally {
            callRefreshListeners()
        }
    }

    override suspend fun setFavorite(sessionId: String, isFavorite: Boolean) {
        val userId = userId ?: throw Unauthorized()
        val favorite = FavoriteData(sessionId)
        try {
            favorites = if (isFavorite) {
                api.postFavorite(favorite, userId)
                val favoriteSession = sessions?.firstOrNull { it.id == sessionId }
                    ?: throw Unauthorized()
                favorites.orEmpty().plus(favoriteSession)
            } else {
                api.deleteFavorite(favorite, userId)
                favorites?.filter { it.id != sessionId }
            }
        } catch (_: Throwable) {
            throw CannotFavorite()
        } finally {
            callRefreshListeners()
        }
    }

    private fun callRefreshListeners() {
        onRefreshListeners.forEach { it() }
    }

    /*
     * Local storage
     */

    private inline fun <reified T : Any> read(key: String, elementSerializer: KSerializer<T>) = settings
        .getString(key, "")
        .takeUnless { it.isBlank() }
        ?.let {
            try {
                Json.parse(elementSerializer, it)
            } catch (_: Throwable) {
                null
            }
        }

    private inline fun <reified T : Any> write(key: String, obj: T?, elementSerializer: KSerializer<T>) {
        settings.putString(key, if (obj == null) "" else Json.stringify(elementSerializer, obj))
    }

    private inline fun <reified T : Any> bindToPreferencesByKey(
        key: String,
        elementSerializer: KSerializer<T>
    ): ReadWriteProperty<Any?, T?> = observable(read(key, elementSerializer)) { _, _, new ->
        write(key, new, elementSerializer)
    }
}