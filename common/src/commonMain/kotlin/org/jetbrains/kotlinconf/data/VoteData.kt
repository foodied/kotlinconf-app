package org.jetbrains.kotlinconf.data

enum class VoteData(val value: Int) {
    BAD(-1),
    OK(0),
    GOOD(1);

    companion object {
        fun valueOf(value: Int): VoteData = values().find { it.value == value }!!
    }
}