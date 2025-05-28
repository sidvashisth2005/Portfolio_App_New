package com.sid.PortfolioAppNew.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid.PortfolioAppNew.ui.screens.home.LinkedInPost
import com.sid.PortfolioAppNew.ui.screens.home.TimelineEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {
    private val _timelineEvents = MutableStateFlow<List<TimelineEvent>>(emptyList())
    val timelineEvents: StateFlow<List<TimelineEvent>> = _timelineEvents.asStateFlow()

    private val _linkedInPosts = MutableStateFlow<List<LinkedInPost>>(emptyList())
    val linkedInPosts: StateFlow<List<LinkedInPost>> = _linkedInPosts.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        // Load timeline events
        _timelineEvents.value = listOf(
            TimelineEvent(
                id = "1",
                title = "SBI Hackathon Finalist",
                description = "Reached finals in the SBI Hackathon 2023",
                date = Date(),
                icon = null
            ),
            TimelineEvent(
                id = "2",
                title = "Android Developer Certification",
                description = "Completed Google's Android Developer Certification",
                date = Date(System.currentTimeMillis() - 86400000 * 30), // 30 days ago
                icon = null
            ),
            TimelineEvent(
                id = "3",
                title = "AR Project Launch",
                description = "Launched first AR project using ARCore",
                date = Date(System.currentTimeMillis() - 86400000 * 60), // 60 days ago
                icon = null
            )
        )

        // Load LinkedIn posts
        _linkedInPosts.value = listOf(
            LinkedInPost(
                id = "1",
                title = "New Project Launch",
                content = "Excited to announce the launch of my latest Android project! Built with Jetpack Compose and Material3.",
                timestamp = Date(),
                mediaUrl = "https://example.com/image.jpg",
                mediaType = null
            ),
            LinkedInPost(
                id = "2",
                title = "Learning Journey",
                content = "Just completed an advanced course on AR development with ARCore. Looking forward to implementing these skills in my portfolio!",
                timestamp = Date(System.currentTimeMillis() - 86400000),
                mediaType = null
            )
        )
    }

    fun refreshLinkedInFeed() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                // TODO: Implement actual LinkedIn API integration
                // For now, just simulate a delay
                kotlinx.coroutines.delay(1000)
                loadInitialData()
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun addTimelineEvent(event: TimelineEvent) {
        _timelineEvents.value = _timelineEvents.value + event
    }

    fun addLinkedInPost(post: LinkedInPost) {
        _linkedInPosts.value = _linkedInPosts.value + post
    }
} 