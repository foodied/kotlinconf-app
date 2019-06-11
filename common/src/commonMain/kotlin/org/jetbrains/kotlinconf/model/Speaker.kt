package org.jetbrains.kotlinconf.model

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.data.*

@Serializable
class Speaker internal constructor(
    private val data: SpeakerData,
    private val findSession: (String) -> Session = { TODO() }
) {
    val id: String = data.id
    val firstName: String = data.firstName
    val lastName: String = data.lastName
    val fullName: String = data.fullName
    val bio: String = data.bio

    val profilePicture: String = data.profilePicture
    val sessions: List<Session> by lazy { data.sessions.map(findSession) }

    val tagLine: String = data.tagLine
    val isTopSpeaker: Boolean = data.isTopSpeaker
    val links: List<LinkData> = data.links
}