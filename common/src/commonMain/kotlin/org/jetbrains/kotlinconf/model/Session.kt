package org.jetbrains.kotlinconf

import io.ktor.util.date.*
import org.jetbrains.kotlinconf.data.*

//private fun ConferenceData.getSession(id: String): Session {
//    val sessionData = sessions.find { it.id == id }
//}
//
//fun ConferenceData.allSessions(): List<Session> = sessions
//    .map { Session.forSession(this, it.id) }
//    .sorted()
//
//fun ConferenceData.favoriteSessions(): List<Session> = favorites
//    .map { it.sessionId }
//    .map { Session.forSession(this, it) }
//    .sorted()
//
//private fun List<Session>.sorted(): List<Session> =
//    sortedWith(compareBy({ it.startsAt?.timestamp }, { it.title }))
