package com.sid.PortfolioAppNew.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

// Use the data classes as defined in HomeScreen.kt
// TimelineEvent: id, title, description, date, icon, imageUrl
// LinkedInPost: id, title, content, timestamp, mediaUrl, mediaType

class HomeViewModel : ViewModel() {
    private val _timelineEvents = MutableStateFlow<List<TimelineEvent>>(emptyList())
    val timelineEvents: StateFlow<List<TimelineEvent>> = _timelineEvents.asStateFlow()

    private val _linkedInPosts = MutableStateFlow<List<LinkedInPost>>(emptyList())
    val linkedInPosts: StateFlow<List<LinkedInPost>> = _linkedInPosts.asStateFlow()

    var isLoading by mutableStateOf(false)
        private set

    init {
        loadTimelineEvents()
        loadLinkedInPosts()
    }

    private fun loadTimelineEvents() {
        viewModelScope.launch {
            isLoading = true
            delay(1000)
            _timelineEvents.value = listOf(
                TimelineEvent(
                    id = "1",
                    title = "Started Portfolio App",
                    description = "Began development of a new portfolio app using Jetpack Compose",
                    date = Date(),
                    icon = null,
                    imageUrl = null
                ),
                TimelineEvent(
                    id = "2",
                    title = "Completed ARX Project",
                    description = "Finished the initial version of the ARX project",
                    date = Date(System.currentTimeMillis() - 86400000),
                    icon = null,
                    imageUrl = null
                ),
                TimelineEvent(
                    id = "3",
                    title = "Joined New Company",
                    description = "Started working at a new company as a Senior Android Developer",
                    date = Date(System.currentTimeMillis() - 172800000),
                    icon = null,
                    imageUrl = null
                )
            )
            isLoading = false
        }
    }

    private fun loadLinkedInPosts() {
        viewModelScope.launch {
            isLoading = true
            delay(1000)
            _linkedInPosts.value = listOf(
                LinkedInPost(
                    id = "1",
                    title = "Excited to share my latest project!",
                    content = "Excited to share my latest project using Jetpack Compose!",
                    timestamp = Date(),
                    mediaUrl = "https://example.com/project.jpg",
                    mediaType = null
                ),
                LinkedInPost(
                    id = "2",
                    title = "New Article Published",
                    content = "Just published a new article about Android development best practices.",
                    timestamp = Date(System.currentTimeMillis() - 86400000),
                    mediaUrl = "https://example.com/article.jpg",
                    mediaType = null
                ),
                LinkedInPost(
                    id = "3",
                    title = "We're Hiring!",
                    content = "Looking for talented Android developers to join our team!",
                    timestamp = Date(System.currentTimeMillis() - 172800000),
                    mediaUrl = null,
                    mediaType = null
                )
            )
            isLoading = false
        }
    }

    fun refreshData() {
        loadTimelineEvents()
        loadLinkedInPosts()
    }
} 