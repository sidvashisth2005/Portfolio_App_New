package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.animation.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.R
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*

@Composable
fun HomeScreen() {
    var isFabExpanded by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        // Background animation
        val backgroundComposition = rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.background_particles)
        )
        LottieAnimation(
            composition = backgroundComposition.value,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Avatar with glow effect
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                val avatarComposition = rememberLottieComposition(
                    LottieCompositionSpec.RawRes(R.raw.avatar_glow)
                )
                LottieAnimation(
                    composition = avatarComposition.value,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.fillMaxSize()
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color.White
                )
            }

            // Title and subtitle with animations
            AnimatedGlowText(
                text = "SIDDHANT VASHISTH",
                fontSize = 32.sp
            )
            
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Android Developer & AR/VR Enthusiast",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )

                    // Quick info
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = NeonBlue,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "B.Tech at JUET • CGPA: 7.95",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // Action buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AnimatedGlowButton(
                    text = "View Projects",
                    icon = Icons.Default.Work,
                    onClick = { }
                )
                AnimatedGlowButton(
                    text = "View Skills",
                    icon = Icons.Default.Code,
                    onClick = { }
                )
                AnimatedGlowButton(
                    text = "Contact Me",
                    icon = Icons.Default.Email,
                    onClick = { }
                )
            }
        }

        // Bottom Navigation
        NavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            containerColor = Color(0xFF1A1A1A),
            tonalElevation = 8.dp
        ) {
            NavigationBarItem(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NeonBlue,
                    selectedTextColor = NeonBlue,
                    indicatorColor = Color(0xFF2A2A2A)
                )
            )
            NavigationBarItem(
                selected = selectedTab == 1,
                onClick = {  },
                icon = { Icon(Icons.Default.Work, contentDescription = "Projects") },
                label = { Text("Projects") },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NeonBlue,
                    selectedTextColor = NeonBlue,
                    indicatorColor = Color(0xFF2A2A2A)
                )
            )
            NavigationBarItem(
                selected = selectedTab == 2,
                onClick = {  },
                icon = { Icon(Icons.Default.Email, contentDescription = "Contact") },
                label = { Text("Contact") },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NeonBlue,
                    selectedTextColor = NeonBlue,
                    indicatorColor = Color(0xFF2A2A2A)
                )
            )
        }

        // FAB Menu
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (isFabExpanded) {
                    // FAB Menu Items
                    FABMenuItem(
                        icon = Icons.Default.Settings,
                        text = "Settings",
                        onClick = { }
                    )
                    FABMenuItem(
                        icon = Icons.Default.Info,
                        text = "About",
                        onClick = { }
                    )
                }
                FloatingActionButton(
                    onClick = { isFabExpanded = !isFabExpanded },
                    containerColor = NeonBlue,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = if (isFabExpanded) Icons.Default.Close else Icons.Default.Menu,
                        contentDescription = if (isFabExpanded) "Close menu" else "Open menu"
                    )
                }
            }
        }
    }
}

@Composable
private fun SkillChip(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = NeonBlue.copy(alpha = 0.1f)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = NeonBlue,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun FABMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = NeonBlue.copy(alpha = 0.1f)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                color = Color.White
            )
        }
        FloatingActionButton(
            onClick = onClick,
            containerColor = NeonBlue,
            contentColor = Color.White,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text
            )
        }
    }
} 