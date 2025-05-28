package com.sid.PortfolioAppNew.data.model

import java.util.Date

data class TimelineEvent(
    val id: String,
    val title: String,
    val description: String,
    val date: Date,
    val icon: Int? = null,
    val imageUrl: String? = null
)

data class LinkedInPost(
    val id: String,
    val title: String,
    val content: String,
    val timestamp: Date,
    val mediaUrl: String? = null,
    val mediaType: MediaType? = null
)

enum class MediaType {
    IMAGE,
    VIDEO
} 