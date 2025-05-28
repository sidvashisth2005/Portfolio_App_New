package com.sid.PortfolioAppNew.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit
import com.sid.PortfolioAppNew.R

@Composable
fun AnimatedGlowText(
    text: String,
    fontSize: TextUnit = 32.sp
) {
    val transition = rememberInfiniteTransition()
    val glow by transition.animateFloat(
        initialValue = 8f,
        targetValue = 16f,
        animationSpec = infiniteRepeatable(
            tween(1800, easing = EaseInOutCubic),
            RepeatMode.Reverse
        )
    )

    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.shadow(
            elevation = glow.dp,
            ambientColor = Color(0xFF00FFE7),
            spotColor = Color(0xFF7F00FF)
        )
    )
}

@Composable
fun AnimatedSkillChip(label: String) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (pressed) 1.1f else 1f)
    val bgColor by animateColorAsState(
        targetValue = if (pressed) Color(0xFF00FFE7).copy(alpha = 0.3f) else Color(0xFF1E1E2F)
    )

    Box(
        modifier = Modifier
            .scale(scale)
            .background(bgColor, shape = RoundedCornerShape(24.dp))
            .clickable { pressed = !pressed }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun GlowingBottomNavIndicator(currentIndex: Int) {
    val indicatorOffset by animateDpAsState(targetValue = (currentIndex * 100).dp)

    Box(
        modifier = Modifier
            .height(4.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .offset(x = indicatorOffset)
            .width(100.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Color(0xFF00FFE7), Color(0xFF7F00FF))),
                shape = RoundedCornerShape(8.dp)
            )
    )
}

@Composable
fun AnimatedGlowButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (pressed) 0.95f else 1f)

    Surface(
        modifier = Modifier
            .scale(scale)
            .shadow(16.dp)
            .clickable { 
                pressed = true
                onClick()
                pressed = false
            },
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
} 