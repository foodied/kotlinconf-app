package org.jetbrains.kotlinconf.data

enum class RatingData(val value: Int) {
    BAD(-1),
    OK(0),
    GOOD(1);

    companion object {
        fun valueOf(value: Int): RatingData = values().find { it.value == value }!!
    }
}