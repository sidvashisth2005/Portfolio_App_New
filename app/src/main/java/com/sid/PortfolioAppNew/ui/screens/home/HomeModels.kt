package com.sid.PortfolioAppNew.ui.screens.home

import java.util.Date

data class TimelineEvent(
    val id: String,
    val title: String,
    val description: String,
    val date: Date,
    val icon: String? = null,
    val imageUrl: String? = null
)

data class LinkedInPost(
    val id: String,
    val title: String,
    val content: String,
    val timestamp: Date,
    val mediaUrl: String? = null,
    val mediaType: String? = null
) 