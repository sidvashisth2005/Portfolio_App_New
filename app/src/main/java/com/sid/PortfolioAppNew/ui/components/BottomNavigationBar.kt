package com.sid.PortfolioAppNew.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sid.PortfolioAppNew.navigation.Screen
import com.sid.PortfolioAppNew.ui.theme.*

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: String?
) {
    val infiniteTransition = rememberInfiniteTransition(label = "nav")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    NavigationBar(
        containerColor = Color(0xFF121212),
        tonalElevation = 4.dp,
        modifier = Modifier
            .height(64.dp)
            .drawBehind {
                if (currentRoute != null) {
                    val selectedIndex = Screen.bottomNavItems.indexOfFirst { it.route == currentRoute }
                    if (selectedIndex != -1) {
                        val itemWidth = size.width / Screen.bottomNavItems.size
                        val startX = selectedIndex * itemWidth
                        
                        drawLine(
                            color = NeonBlue.copy(alpha = glowAlpha),
                            start = Offset(startX, size.height - 2.dp.toPx()),
                            end = Offset(startX + itemWidth, size.height - 2.dp.toPx()),
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                }
            }
    ) {
        Screen.bottomNavItems.forEach { screen ->
            val selected = currentRoute == screen.route
            
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label,
                        tint = if (selected) NeonBlue else Color.White.copy(alpha = 0.6f)
                    )
                },
                label = {
                    Text(
                        text = screen.label,
                        color = if (selected) NeonBlue else Color.White.copy(alpha = 0.6f)
                    )
                },
                selected = selected,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NeonBlue,
                    selectedTextColor = NeonBlue,
                    indicatorColor = Color(0xFF1A1A1A)
                )
            )
        }
    }
} 