package org.jetbrains.kotlinconf.model

import kotlinx.serialization.Serializable

@Serializable
class Room(val id: Int, val name: String, val sort: Int)