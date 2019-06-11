package org.jetbrains.kotlinconf.presentation

import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.data.*

interface SessionDetailsView : BaseView {
    fun updateSession(session: Session)
    fun updateRating(rating: RatingData?)
    fun setRatingClickable(clickable: Boolean)
    fun updateFavorite(isFavorite: Boolean)
}