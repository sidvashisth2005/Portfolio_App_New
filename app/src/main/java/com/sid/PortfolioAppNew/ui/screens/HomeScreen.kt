package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.R
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*
import com.sid.PortfolioAppNew.ui.navigation.AnimatedPageTransition
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.draw.scale
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.foundation.clickable

@Composable
fun HomeScreen(
    onNavigateToProjects: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToTimeline: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1000)
        isLoading = false
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
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Hero section with cyberpunk text
                        AnimatedVisibility(
                            visible = !isLoading,
                            enter = fadeIn() + slideInVertically { it / 2 }
                        ) {
                            CyberpunkCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 32.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CyberpunkText(
                                        text = "Welcome to My Portfolio",
                                        style = MaterialTheme.typography.headlineLarge,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    CyberpunkText(
                                        text = "Android Developer & AR/VR Enthusiast",
                                        style = MaterialTheme.typography.titleMedium,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                        // Navigation buttons with staggered animations
                        val buttons = listOf(
                            "Projects" to onNavigateToProjects,
                            "Skills" to onNavigateToSkills,
                            "Timeline" to onNavigateToTimeline,
                            "About" to onNavigateToAbout
                        )

                        buttons.forEachIndexed { index, (text, onClick) ->
                            AnimatedVisibility(
                                visible = !isLoading,
                                enter = fadeIn(
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        delayMillis = index * 100
                                    )
                                ) + slideInVertically(
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        delayMillis = index * 100
                                    )
                                ) { it / 2 }
                            ) {
                                NeonButton(
                                    text = text,
                                    onClick = onClick,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }

                        // Recent activity section
                        AnimatedVisibility(
                            visible = !isLoading,
                            enter = fadeIn(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    delayMillis = 400
                                )
                            ) + slideInVertically(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    delayMillis = 400
                                )
                            ) { it / 2 }
                        ) {
                            CyberpunkCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 32.dp)
                            ) {
                                Column {
                                    CyberpunkText(
                                        text = "Recent Activity",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        ActivityItem(
                                            icon = R.raw.github_activity,
                                            text = "GitHub"
                                        )
                                        ActivityItem(
                                            icon = R.raw.linkedin_activity,
                                            text = "LinkedIn"
                                        )
                                        ActivityItem(
                                            icon = R.raw.medium_activity,
                                            text = "Medium"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ActivityItem(
    icon: Int,
    text: String
) {
    var isHovered by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.1f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .scale(scale)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false),
                onClick = { isHovered = !isHovered }
            )
    ) {
        CyberpunkLottieAnimation(
            modifier = Modifier.size(48.dp),
            lottieResId = icon
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            color = Color.White.copy(alpha = 0.7f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
} 