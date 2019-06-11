package org.jetbrains.kotlinconf.presentation

import org.jetbrains.kotlinconf.*

interface SessionsView : BaseView {
    fun onUpdateStarted()
    fun onUpdateFinish(sessions: List<Session>, favorites: List<Session>)
}
