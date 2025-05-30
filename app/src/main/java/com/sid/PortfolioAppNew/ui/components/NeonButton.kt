package com.sid.PortfolioAppNew.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun NeonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentColor: Color = Color.White,
    lottieResId: Int? = null
) {
    var isHovered by remember { mutableStateOf(false) }
    
    val glowIntensity by animateFloatAsState(
        targetValue = if (isHovered) 1f else 0.5f,
        animationSpec = tween(300),
        label = "glow"
    )
    
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.05f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
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
                    )
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
            .clickable(enabled = enabled, onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (lottieResId != null) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(lottieResId)
                )
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                color = contentColor,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
} 