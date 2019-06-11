package org.jetbrains.kotlinconf.presentation

import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.model.*

interface NavigationManager {
    fun showSessions()
    fun showSpeakers()
    fun showFAQ()
    fun showVenueMap()
    fun showSpeaker(speaker: Speaker)
    fun showSessionDetails(session: Session)
    fun showPrivacyPolicyDialog()
}