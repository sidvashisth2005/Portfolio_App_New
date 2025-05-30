package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.R
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun TimelineScreen(onNavigateBack: () -> Unit) {
    var isLoading by remember { mutableStateOf(true) }
    var timelineEvents by remember { mutableStateOf<List<TimelineEvent>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Simulate loading
        kotlinx.coroutines.delay(1500)
        isLoading = false
        // TODO: Replace with actual timeline data loading
        timelineEvents = sampleTimelineEvents
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        // Background animation
        AnimatedLottieBackground(
            modifier = Modifier.fillMaxSize(),
            lottieResId = R.raw.code_background,
            alpha = 0.2f
        )

        AnimatedPageTransition {
            when {
                isLoading -> {
                    LoadingAnimation(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                timelineEvents.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        EmptyStateAnimation(
                            modifier = Modifier.size(200.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No timeline events yet!",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                        Text(
                            text = "Stay tuned for updates",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(timelineEvents) { event ->
                            AnimatedTimelineEvent(event)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimatedTimelineEvent(event: TimelineEvent) {
    var isHovered by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable { isHovered = !isHovered },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Timeline Animation
            TimelineAnimation(
                modifier = Modifier.size(48.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = event.date,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = event.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}

private data class TimelineEvent(
    val title: String,
    val date: String,
    val description: String
)

private val sampleTimelineEvents = listOf(
    TimelineEvent(
        title = "Senior Android Developer",
        date = "2023 - Present",
        description = "Leading Android development for AR/VR applications at Tech Innovations Inc."
    ),
    TimelineEvent(
        title = "Android Developer",
        date = "2021 - 2023",
        description = "Developed and maintained multiple Android applications using Kotlin and Jetpack Compose."
    ),
    TimelineEvent(
        title = "Junior Developer",
        date = "2019 - 2021",
        description = "Started career as a junior developer, focusing on Android and web development."
    )
) 