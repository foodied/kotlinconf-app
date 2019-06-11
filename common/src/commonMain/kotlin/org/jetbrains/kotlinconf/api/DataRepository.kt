package org.jetbrains.kotlinconf.model

import kotlinx.serialization.*
import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.api.*
import org.jetbrains.kotlinconf.data.*
import org.jetbrains.kotlinconf.storage.*

/**
 * [DataRepository] handles data and builds model.
 */
class DataRepository(
    private val userId: String,
    endPoint: String,
    storage: ApplicationStorage
) {
    private val api = ApiAdapter(endPoint, userId)
    private val model: Conference by storage.bind("model", Conference.serializer()) { Conference() }

    var privacyPolicyAccepted: Boolean by storage.bind("privacyPolicyAccepted", Boolean.serializer()) { false }

    private var loggedIn: Boolean = false

    suspend fun reloadModel(): Conference {
        val state = try {
            if (!loggedIn) {
                api.createUser(userId)
                loggedIn = true
            }
            api.getAll(userId)
        } catch (cause: Throwable) {
            throw UpdateProblem()
        }

        model.update(state)
        return model
    }

    suspend fun setRating(session: Session, rating: RatingData?) {
        if (rating != null) {
            val vote = VoteData(session.id, rating)
            api.postVote(userId, vote)
        } else {
            api.deleteVote(userId, session.id)
        }
    }

    suspend fun removeRating(session: Session) {
        api.deleteVote(userId, session.id)
    }

    suspend fun setFavorite(session: Session, isFavorite: Boolean) {
        if (isFavorite) {
            api.postFavorite(userId, session.id)
        } else {
            api.deleteFavorite(userId, session.id)
        }
    }
}
