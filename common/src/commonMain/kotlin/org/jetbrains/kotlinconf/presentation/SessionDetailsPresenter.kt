package org.jetbrains.kotlinconf.presentation

import kotlinx.coroutines.*
import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.data.*
import org.jetbrains.kotlinconf.model.*
import kotlin.coroutines.*

/**
 * Single session presenter.
 * Keep separate local values for favorite and rating.
 */
class SessionDetailsPresenter(
    private val view: SessionDetailsView,
    private val session: Session,
    private val repository: DataRepository,
    context: CoroutineContext = Dispatchers.Main
) : CoroutinePresenter(view, context) {
    private var localRating: RatingData? = session.rating
    private var localFavorite: Boolean = session.isFavorite

    init {
        view.updateSession(session)
    }

    /**
     * Handle rating button event.
     */
    fun onRatingButtonClicked(clicked: RatingData) {
        launch {
            view.setRatingClickable(false)

            localRating = if (localRating != clicked) clicked else null
            repository.setRating(session, clicked)
            view.updateRating(localRating)
        }.invokeOnCompletion {
            if (it != null) {
                view.showError(it)
                localRating = session.rating
            }

            view.setRatingClickable(true)
            view.updateRating(localRating)
        }
    }

    /**
     * Handle rating button event.
     */
    fun onFavoriteButtonClicked() {
        launch {
            repository.setFavorite(session, !localFavorite)
            view.updateFavorite(localFavorite)
        }.invokeOnCompletion {
            if (it != null) localFavorite = session.isFavorite
            view.updateFavorite(localFavorite)
        }
    }
}
