package com.sid.PortfolioAppNew.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun CyberpunkCard(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    var isHovered by remember { mutableStateOf(false) }
    
    val glowIntensity by animateFloatAsState(
        targetValue = if (isHovered) 1f else 0.5f,
        animationSpec = tween(300),
        label = "glow"
    )
    
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "border")
    val borderRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = if (isHovered) 24.dp else 12.dp,
                spotColor = Color(0xFF00FF00).copy(alpha = glowIntensity * 0.3f)
            )
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 2.dp,
                brush = Brush.sweepGradient(
                    colors = listOf(
                        Color(0xFF00FF00).copy(alpha = glowIntensity),
                        Color(0xFF00FFFF).copy(alpha = glowIntensity),
                        Color(0xFF00FF00).copy(alpha = glowIntensity)
                    ),
                    center = androidx.compose.ui.geometry.Offset.Zero
                ),
                shape = MaterialTheme.shapes.medium
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1A1A1A),
                        Color(0xFF2A2A2A)
                    )
                ),
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp)
    ) {
        content()
    }
} 