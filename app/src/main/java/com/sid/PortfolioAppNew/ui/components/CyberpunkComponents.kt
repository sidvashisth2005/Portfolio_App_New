package com.sid.PortfolioAppNew.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.ui.theme.*

@Composable
fun GlowingCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowAlpha = infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = NeonBlue.copy(alpha = glowAlpha.value)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkSurface.copy(alpha = 0.8f),
                        DarkSurface.copy(alpha = 0.6f)
                    )
                )
            ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun NeonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    lottieResId: Int? = null
) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowAlpha = infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = NeonBlue.copy(alpha = glowAlpha.value)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = NeonPrimary.copy(alpha = 0.8f)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (lottieResId != null) {
                val composition = rememberLottieComposition(
                    LottieCompositionSpec.RawRes(lottieResId)
                )
                LottieAnimation(
                    composition = composition.value,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)
        }
    }
}

@Composable
fun GlassmorphicBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkBackground,
                        DarkBackground.copy(alpha = 0.8f)
                    )
                )
            )
    ) {
        content()
    }
}

@Composable
fun AnimatedLottieBackground(
    modifier: Modifier = Modifier,
    lottieResId: Int
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(lottieResId)
    )
    
    LottieAnimation(
        composition = composition.value,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}

@Composable
fun GlowButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .scale(scale)
            .drawBehind {
                drawRoundRect(
                    color = NeonBlue.copy(alpha = 0.3f),
                    style = Stroke(width = 2f)
                )
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (icon != null) {
                icon()
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun GlowText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 32,
    fontWeight: FontWeight = FontWeight.SemiBold
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Text(
        text = text,
        modifier = modifier.drawBehind {
            drawRoundRect(
                color = NeonBlue.copy(alpha = glowAlpha),
                style = Stroke(width = 2f)
            )
        },
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        color = Color.White
    )
}

@Composable
fun GlowingNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        color = DarkSurface.copy(alpha = 0.9f),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem("Home", currentRoute == "home", onNavigate)
            NavItem("Projects", currentRoute == "projects", onNavigate)
            NavItem("Contact", currentRoute == "contact", onNavigate)
        }
    }
}

@Composable
private fun NavItem(
    route: String,
    isSelected: Boolean,
    onNavigate: (String) -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "navGlow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "navGlow"
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .drawBehind {
                if (isSelected) {
                    drawRoundRect(
                        color = NeonBlue.copy(alpha = glowAlpha),
                        style = Stroke(width = 2f)
                    )
                }
            }
    ) {
        TextButton(
            onClick = { onNavigate(route) },
            colors = ButtonDefaults.textButtonColors(
                contentColor = if (isSelected) NeonBlue else Color.White
            )
        ) {
            Text(
                text = route,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
} 