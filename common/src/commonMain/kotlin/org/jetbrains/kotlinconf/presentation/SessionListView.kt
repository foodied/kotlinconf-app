package org.jetbrains.kotlinconf.presentation

import org.jetbrains.kotlinconf.*

interface SessionListView : BaseView {
    var isUpdating: Boolean
    fun onUpdate(sessions: List<Session>, favorites: List<Session>)
}
