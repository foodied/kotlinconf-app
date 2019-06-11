package org.jetbrains.kotlinconf.presentation

import kotlinx.coroutines.*
import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.model.*
import kotlin.coroutines.*

class SessionsPresenter(
    private val view: SessionsView,
    private val repository: DataRepository,
    private val navigationManager: NavigationManager,
    context: CoroutineContext = Dispatchers.Main
) : CoroutinePresenter(view, context) {

    fun onCreate() {
        updateData()
    }

    fun showSessionDetails(session: Session) {
        navigationManager.showSessionDetails(session)
    }

    fun onPullRefresh() {
        updateData()
    }

    private fun updateData(): Unit {
        launch {
            view.onUpdateStarted()
            val model = repository.reloadModel()
            view.onUpdateFinish(model.sessions, model.favorites)
        }
    }
}