package com.sid.PortfolioAppNew.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.R
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
fun CyberpunkLottieAnimation(
    modifier: Modifier = Modifier,
    lottieResId: Int,
    iterations: Int = LottieConstants.IterateForever,
    speed: Float = 1f,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = 1f
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(lottieResId)
    )
    
    val infiniteTransition = rememberInfiniteTransition()
    val glowAlpha = infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = NeonBlue.copy(alpha = glowAlpha.value)
            )
    ) {
        LottieAnimation(
            composition = composition.value,
            iterations = iterations,
            speed = speed,
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha),
            contentScale = contentScale
        )
    }
}

@Composable
fun AnimatedLottieBackground(
    modifier: Modifier = Modifier,
    lottieResId: Int,
    alpha: Float = 0.3f
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(lottieResId)
    )
    
    LottieAnimation(
        composition = composition.value,
        iterations = LottieConstants.IterateForever,
        modifier = modifier.alpha(alpha)
    )
}

@Composable
fun LoadingLottieAnimation(
    modifier: Modifier = Modifier,
    lottieResId: Int = R.raw.loading
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CyberpunkLottieAnimation(
            modifier = Modifier.size(120.dp),
            lottieResId = lottieResId
        )
    }
}

@Composable
fun ErrorLottieAnimation(
    modifier: Modifier = Modifier,
    lottieResId: Int = R.raw.error,
    message: String = "Something went wrong"
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CyberpunkLottieAnimation(
            modifier = Modifier.size(200.dp),
            lottieResId = lottieResId
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
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

@Composable
fun AnimatedGlowText(
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

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Text(
        text = text,
        modifier = modifier
            .scale(scale)
            .drawBehind {
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
fun AnimatedSkillCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int,
    content: @Composable () -> Unit
) {
    var isHovered by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.05f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    Card(
        modifier = modifier
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        isHovered = true
                        try {
                            awaitRelease()
                        } finally {
                            isHovered = false
                        }
                    }
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CyberpunkLottieAnimation(
                    modifier = Modifier.size(32.dp),
                    lottieResId = icon
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

@Composable
fun AnimatedProjectCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    icon: Int,
    technologies: List<Pair<String, Int>>,
    onClick: () -> Unit
) {
    var isHovered by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

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

    Card(
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = NeonBlue.copy(alpha = glowAlpha)
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        isHovered = true
                        try {
                            awaitRelease()
                        } finally {
                            isHovered = false
                        }
                    }
                )
            }
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CyberpunkLottieAnimation(
                    modifier = Modifier.size(48.dp),
                    lottieResId = icon
                )
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Technologies
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                technologies.forEach { (tech, icon) ->
                    AnimatedTechnologyChip(
                        name = tech,
                        icon = icon
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedTechnologyChip(
    name: String,
    icon: Int
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

    Surface(
        modifier = Modifier
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        isHovered = true
                        try {
                            awaitRelease()
                        } finally {
                            isHovered = false
                        }
                    }
                )
            },
        shape = RoundedCornerShape(16.dp),
        color = NeonBlue.copy(alpha = 0.1f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            CyberpunkLottieAnimation(
                modifier = Modifier.size(16.dp),
                lottieResId = icon
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.labelSmall,
                color = NeonBlue
            )
        }
    }
}

@Composable
fun AnimatedLoadingIndicator(
    modifier: Modifier = Modifier,
    lottieResId: Int = R.raw.loading
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotate")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ),
        label = "rotation"
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                rotationZ = rotation
            },
        contentAlignment = Alignment.Center
    ) {
        CyberpunkLottieAnimation(
            modifier = Modifier.size(48.dp),
            lottieResId = lottieResId
        )
    }
}

@Composable
fun AnimatedPageTransition(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        ) + fadeIn(
            animationSpec = tween(500)
        ),
        exit = slideOutVertically(
            targetOffsetY = { -it },
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        ) + fadeOut(
            animationSpec = tween(500)
        ),
        modifier = modifier
    ) {
        content()
    }
} 