package org.jetbrains.kotlinconf.model

import kotlinx.serialization.*
import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.data.*

@Serializable
class Conference {
    private val _sessions = mutableListOf<Session>()
    private val _speakers = mutableListOf<Speaker>()

    val sessions: List<Session> = _sessions

    val favorites: List<Session>
        get() = _sessions.filter { it.isFavorite }

    val speakers: List<Speaker> = _speakers

    fun update(vote: VoteData) {
        val session = _sessions.find { it.id == vote.sessionId }
            ?: error("Invalid vote for session: ${vote.sessionId}")

        session.rating = vote.rating
    }

    fun addFavorite(sessionId: String) {
        _sessions.find { it.id == sessionId }?.isFavorite = true
    }

    fun removeFavorite(sessionId: String) {
        _sessions.find { it.id == sessionId }?.isFavorite = false
    }

    fun update(data: ConferenceData) {
        _sessions.clear()
        _speakers.clear()

        val favorites: Set<String> = data.favorites.toSet()
        val votes: Map<String, RatingData?> = data.votes.map { it.sessionId to it.rating }.toMap()
        val rooms: Map<Int, Room> = data.rooms.map { it.id to Room(it.id, it.name, it.sort) }.toMap()

        val speakerById: (String) -> Speaker = { id: String -> speakers.find { it.id == id }!! }
        val sessionById: (String) -> Session = { id: String -> sessions.find { it.id == id }!! }

        for (sessionData in data.sessions) {
            val id = sessionData.id
            val isFavorite = id in favorites

//            _sessions.add(Session(sessionData, isFavorite, votes[id], rooms[id]!!, speakerById))
        }

        for (speakerData in data.speakers) {
            _speakers.add(Speaker(speakerData, sessionById))
        }

        _sessions.sortBy { it.startsAt }
        _speakers.sortBy { it.fullName }
    }
}