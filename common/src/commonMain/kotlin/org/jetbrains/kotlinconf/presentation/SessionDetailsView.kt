package org.jetbrains.kotlinconf.presentation

import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.data.*

interface SessionDetailsView : BaseView {
    fun updateView(isFavorite: Boolean, session: Session)
    fun setupRatingButtons(rating: VoteData?)
    fun setRatingClickable(clickable: Boolean)
}