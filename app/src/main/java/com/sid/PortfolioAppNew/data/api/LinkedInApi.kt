package com.sid.PortfolioAppNew.data.api

import retrofit2.http.GET
import retrofit2.http.Query

data class LinkedInPost(
    val id: String,
    val title: String,
    val content: String,
    val timestamp: String,
    val mediaUrl: String?,
    val mediaType: MediaType?
)

enum class MediaType {
    IMAGE,
    VIDEO
}

interface LinkedInApi {
    @GET("posts")
    suspend fun getPosts(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): List<LinkedInPost>
}

object LinkedInApiConfig {
    const val BASE_URL = "https://api.linkedin.com/v2/"
    const val ACCESS_TOKEN = "your_access_token" // Replace with your LinkedIn API token
} 