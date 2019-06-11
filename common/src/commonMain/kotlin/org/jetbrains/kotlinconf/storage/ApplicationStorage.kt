package org.jetbrains.kotlinconf.storage

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.jetbrains.kotlinconf.model.*
import kotlin.properties.*
import kotlin.properties.Delegates.observable

interface ApplicationStorage {
    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun putString(key: String, value: String)
    fun getString(key: String): String?
}

inline fun <reified T> ApplicationStorage.bind(
    key: String,
    serializer: KSerializer<T>,
    block: () -> T
): ReadWriteProperty<DataRepository, T> {
    val value = getString(key)?.let { Json.parse(serializer, it) } ?: block()
    /**
     * Store when application loads persists state.
     */
    return TODO()
}
/*
    /*
     * Local storage
     */
    private inline fun <reified T : Any> read(key: String, elementSerializer: KSerializer<T>) = storage
        .getString(key, "")
        .takeUnless { it.isBlank() }
        ?.let {
            try {
                Json.parse(elementSerializer, it)
            } catch (_: Throwable) {
                null
            }
        }

    private inline fun <reified T : Any> write(key: String, obj: T?, elementSerializer: KSerializer<T>) {
        storage.putString(key, if (obj == null) "" else Json.stringify(elementSerializer, obj))
    }

    private inline fun <reified T : Any> preferences(): ReadWriteProperty<Any?, T?> {
        val key = T::class.simpleName!!
        val serializer = T::serializer

        observable(read(key, elementSerializer)) { _, _, new ->
            write(key, new, elementSerializer)
        }
    }
 */
