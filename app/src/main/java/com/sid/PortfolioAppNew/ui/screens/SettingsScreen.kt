package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit,
    notificationsEnabled: Boolean,
    onNotificationsChanged: (Boolean) -> Unit,
    highQualityAR: Boolean,
    onHighQualityARChanged: (Boolean) -> Unit
) {
    GlassmorphicBackground(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top Bar
            TopAppBar(
                title = { Text("Settings", style = MaterialTheme.typography.headlineMedium) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkSurface.copy(alpha = 0.8f)
                )
            )

            // Main Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Theme Settings
                GlowingCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "Appearance",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonBlue
                        )
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Dark Theme",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Switch(
                                checked = isDarkTheme,
                                onCheckedChange = onThemeChanged,
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = NeonPink,
                                    checkedTrackColor = NeonBlue.copy(alpha = 0.5f),
                                    uncheckedThumbColor = DarkSurface,
                                    uncheckedTrackColor = DarkSurface.copy(alpha = 0.5f)
                                )
                            )
                        }
                    }
                }

                // Notification Settings
                GlowingCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "Notifications",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonGreen
                        )
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Enable Notifications",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Switch(
                                checked = notificationsEnabled,
                                onCheckedChange = onNotificationsChanged,
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = NeonPink,
                                    checkedTrackColor = NeonGreen.copy(alpha = 0.5f),
                                    uncheckedThumbColor = DarkSurface,
                                    uncheckedTrackColor = DarkSurface.copy(alpha = 0.5f)
                                )
                            )
                        }
                    }
                }

                // AR Settings
                GlowingCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "AR Experience",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonPurple
                        )
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "High Quality AR",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Switch(
                                checked = highQualityAR,
                                onCheckedChange = onHighQualityARChanged,
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = NeonPink,
                                    checkedTrackColor = NeonPurple.copy(alpha = 0.5f),
                                    uncheckedThumbColor = DarkSurface,
                                    uncheckedTrackColor = DarkSurface.copy(alpha = 0.5f)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
} 