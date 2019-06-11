package org.jetbrains.kotlinconf.presentation

import kotlinx.coroutines.*
import kotlin.coroutines.*

open class CoroutinePresenter(
    private val baseView: BaseView,
    context: CoroutineContext = Dispatchers.Main
) : CoroutineScope {
    private val job = Job(context[Job])

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        baseView.showError(throwable)
    }

    override val coroutineContext: CoroutineContext = context + job + exceptionHandler

    open fun onDestroy() {
        job.cancel()
    }
}