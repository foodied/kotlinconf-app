package org.jetbrains.kotlinconf.presentation

import org.jetbrains.kotlinconf.model.*

class PrivacyPolicyPresenter(
    private val repository: DataRepository
) {
    fun onAcceptPrivacyPolicyClicked() {
        repository.privacyPolicyAccepted = true
    }
}