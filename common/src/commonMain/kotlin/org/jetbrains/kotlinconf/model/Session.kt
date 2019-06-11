package org.jetbrains.kotlinconf

import io.ktor.util.date.*
import kotlinx.serialization.*
import org.jetbrains.kotlinconf.data.*
import org.jetbrains.kotlinconf.model.*

@Serializable
class Session internal constructor(
    private val data: SessionData,
    var isFavorite: Boolean,
    var rating: RatingData?,
    val room: Room,
    private val findSpeaker: (String) -> Speaker = { TODO()}
) {
    val id: String = data.id
    val title: String = data.title
    val category: String = data.categoryItems.joinToString()
    val descriptionText: String = data.descriptionText
    val speakers: List<Speaker> by lazy { data.speakers.map(findSpeaker) }

    @Transient
    val startsAt: GMTDate = data.startsAt.parseDate()

    @Transient
    val endsAt: GMTDate = data.endsAt.parseDate()

    override fun hashCode(): Int = id.hashCode()
}