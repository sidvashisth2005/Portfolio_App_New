package com.sid.PortfolioAppNew.ui.components

import androidx.compose.animation.*
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sid.PortfolioAppNew.navigation.Screen
import com.sid.PortfolioAppNew.ui.theme.*
import com.airbnb.lottie.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFF1A1A1A),
        contentColor = NeonPrimary,
        modifier = Modifier.height(64.dp)
    ) {
        Screen.bottomNavItems.forEach { screen ->
            val selected = currentRoute == screen.route
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(screen.route) },
                icon = {
                    if (selected) {
                        // Show Lottie animation when selected
                        val composition by rememberLottieComposition(
                            LottieCompositionSpec.Asset(
                                when (screen) {
                                    Screen.Home -> "Animation - 1748582181078.json"
                                    Screen.Projects -> "Animation - 1748582018683.json"
                                    Screen.About -> "avatar.json"
                                    else -> "LOADING Animation.json"
                                }
                            )
                        )
                        val lottieAnimatable = rememberLottieAnimatable()
                        LaunchedEffect(composition) {
                            lottieAnimatable.animate(
                                composition = composition,
                                iterations = LottieConstants.IterateForever
                            )
                        }
                        LottieAnimation(
                            composition = composition,
                            progress = { lottieAnimatable.progress },
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            imageVector = screen.getIcon(),
                            contentDescription = screen.getLabel()
                        )
                    }
                },
                label = {
                    Text(
                        text = screen.getLabel(),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NeonPrimary,
                    unselectedIconColor = Color.White.copy(alpha = 0.6f),
                    selectedTextColor = NeonPrimary,
                    unselectedTextColor = Color.White.copy(alpha = 0.6f),
                    indicatorColor = Color(0xFF2A2A2A)
                )
            )
        }
    }
} 