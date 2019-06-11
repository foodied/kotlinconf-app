package org.jetbrains.kotlinconf.presentation

import org.jetbrains.kotlinconf.model.*

class MainScreenPresenter(
    private val navigationManager: NavigationManager,
    private val repository: DataRepository
) {
    fun onCreate() {
        if (!repository.privacyPolicyAccepted) {
            navigationManager.showPrivacyPolicyDialog()
        }
    }
}