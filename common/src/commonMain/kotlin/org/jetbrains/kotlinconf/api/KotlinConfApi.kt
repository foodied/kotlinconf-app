package org.jetbrains.kotlinconf.api

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.response.*
import io.ktor.http.*
import kotlinx.io.core.*
import org.jetbrains.kotlinconf.data.*

class KotlinConfApi(private val endPoint: String, private val userId: String) {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(ConferenceData::class, ConferenceData.serializer())
                setMapper(FavoriteData::class, FavoriteData.serializer())
                setMapper(VoteData::class, VoteData.serializer())
            }
        }
    }

    suspend fun createUser(userId: String): Boolean = client.request<HttpResponse> {
        apiUrl("users")
        method = HttpMethod.Post
        body = userId
    }.use {
        it.status.isSuccess()
    }

    suspend fun getAll(userId: String?): ConferenceData = client.get {
        apiUrl("all", userId)
    }

    suspend fun postFavorite(favorite: FavoriteData, userId: String): Unit = client.post {
        apiUrl("favorites", userId)
        json()
        body = favorite
    }

    suspend fun deleteFavorite(favorite: FavoriteData, userId: String): Unit = client.delete {
        apiUrl("favorites", userId)
        json()
        body = favorite
    }

    suspend fun postVote(vote: VoteData, userId: String): Unit = client.post {
        apiUrl("votes", userId)
        json()
        body = vote
    }

    suspend fun deleteVote(vote: VoteData, userId: String): Unit = client.delete {
        apiUrl("votes", userId)
        json()
        body = vote
    }

    private fun HttpRequestBuilder.json() {
        contentType(ContentType.Application.Json)
    }

    private fun HttpRequestBuilder.apiUrl(path: String, userId: String? = null) {
        if (userId != null) {
            header(HttpHeaders.Authorization, "Bearer $userId")
        }
        header(HttpHeaders.CacheControl, "no-cache")
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}
