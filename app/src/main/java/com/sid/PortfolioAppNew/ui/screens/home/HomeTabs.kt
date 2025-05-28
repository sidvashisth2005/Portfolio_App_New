package com.sid.PortfolioAppNew.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.R
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import java.util.*

@Composable
fun TimelineTab() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(sampleTimelineEvents) { event ->
            TimelineCard(event)
        }
    }
}

@Composable
fun LinkedInFeedTab() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(sampleLinkedInPosts) { post ->
            LinkedInPostCard(post)
        }
    }
}

@Composable
fun GitHubPlaceholderTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val composition = rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.github_placeholder)
            )
            
            LottieAnimation(
                composition = composition.value,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(200.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "GitHub Integration Coming Soon",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        }
    }
}

@Composable
private fun TimelineCard(event: TimelineEvent) {
    NeonCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event.date.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = NeonPrimary
            )
        }
    }
}

@Composable
private fun LinkedInPostCard(post: LinkedInPost) {
    NeonCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile picture placeholder
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.DarkGray, shape = MaterialTheme.shapes.small),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = post.title.first().toString(),
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        text = post.timestamp.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

// Sample data
private val sampleTimelineEvents = listOf(
    TimelineEvent(
        id = "1",
        title = "Android Developer Certification",
        description = "Completed Google's Android Developer Certification",
        date = Date(),
        icon = null,
        imageUrl = null
    ),
    TimelineEvent(
        id = "2",
        title = "Senior Developer Promotion",
        description = "Promoted to Senior Android Developer",
        date = Date(System.currentTimeMillis() - 86400000),
        icon = null,
        imageUrl = null
    ),
    TimelineEvent(
        id = "3",
        title = "Jetpack Compose Mastery",
        description = "Completed advanced Jetpack Compose course",
        date = Date(System.currentTimeMillis() - 172800000),
        icon = null,
        imageUrl = null
    )
)

private val sampleLinkedInPosts = listOf(
    LinkedInPost(
        id = "1",
        title = "SID",
        content = "Excited to share my latest project using Jetpack Compose and Material 3!",
        timestamp = Date(),
        mediaUrl = null,
        mediaType = null
    ),
    LinkedInPost(
        id = "2",
        title = "SID",
        content = "Just completed a challenging AR implementation for our portfolio app.",
        timestamp = Date(System.currentTimeMillis() - 86400000),
        mediaUrl = null,
        mediaType = null
    ),
    LinkedInPost(
        id = "3",
        title = "SID",
        content = "Looking forward to speaking at the Android Developers Conference next month!",
        timestamp = Date(System.currentTimeMillis() - 172800000),
        mediaUrl = null,
        mediaType = null
    )
) 