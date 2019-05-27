package org.jetbrains.kotlinconf.data

import kotlinx.serialization.*

@Serializable
data class QuestionAnswerData(
    val questionId: Int,
    val answerValue: String
)
