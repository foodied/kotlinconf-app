package org.jetbrains.kotlinconf.model

import io.ktor.util.date.*
import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.data.*

class SessionBlock(val startTime: GMTDate, val sessions: List<Session>)

class Room(
    val id: String,
    val name: String,
    val sort: Int
) {
    val sessions: List<Session> = TODO()
}

class Speaker

class Session internal constructor(
    val id: String,
    val title: String,
    val category: String,
    val descriptionText: String,
    startsAt: String,
    endsAt: String,
    val room: Room,
    val isFavourite: Boolean,
    val speakers: List<Speaker>
) {
    val startsAt: GMTDate = startsAt.parseDate()
    val endsAt: GMTDate = endsAt.parseDate()
}

class ConferenceModel(
    val sessionBlocks: List<SessionBlock>
)

fun ConferenceData.buildModel(): ConferenceModel {
    val x: ConferenceModel = TODO()
    TODO()
}

//    companion object {
//        fun forSession(all: ConferenceData, sessionId: String): Session {
//            val briefSession = all.sessions.first { it.id == sessionId }
//            val speakerMap = all.speakers.associateBy { it.id }
//            val roomMap = all.rooms.associateBy { it.id }
//            val categoryMap = all.categories
//                .flatMap { it.items }
//                .associateBy { it.id }
//
//            return forSession(briefSession,
//                speakerProvider = { id -> speakerMap[id] },
//                categoryProvider = { id -> categoryMap[id] },
//                roomProvider = { id -> roomMap[id]!! }
//            )
//        }
//
//        private fun forSession(
//            briefSession: SessionData,
//            speakerProvider: (String) -> SpeakerData?,
//            categoryProvider: (Int) -> CategoryItemData?,
//            roomProvider: (Int) -> RoomData
//        ): Session {
//            val startsAt = briefSession.startsAt
//            val endsAt = briefSession.endsAt
//
//            return Session(
//                id = briefSession.id,
//                title = briefSession.title,
//                category = briefSession.categoryItems.map(categoryProvider).firstOrNull()?.name ?: "",
//                descriptionText = briefSession.descriptionText ?: "",
//                startsAtStr = startsAt ?: "",
//                endsAtStr = endsAt ?: "",
//                speakers = briefSession.speakers.mapNotNull { speakerProvider(it) },
//                room = briefSession.roomId?.let(roomProvider)?.name ?: ""
//            )
//        }
//    }
