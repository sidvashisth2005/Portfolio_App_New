package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    onNavigateBack: () -> Unit,
    onEmailClick: () -> Unit,
    onPhoneClick: () -> Unit,
    onLocationClick: () -> Unit,
    onLinkedInClick: () -> Unit,
    onGitHubClick: () -> Unit,
    onTwitterClick: () -> Unit
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
                title = { Text("Contact", style = MaterialTheme.typography.headlineMedium) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
                // Contact Info Section
                GlowingCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "Contact Information",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonBlue
                        )
                        
                        // Email
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = "Email",
                                tint = NeonPink
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            TextButton(onClick = onEmailClick) {
                                Text(
                                    "example@email.com",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }

                        // Phone
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Phone,
                                contentDescription = "Phone",
                                tint = NeonGreen
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            TextButton(onClick = onPhoneClick) {
                                Text(
                                    "+1 234 567 890",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }

                        // Location
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = NeonPurple
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            TextButton(onClick = onLocationClick) {
                                Text(
                                    "New York, USA",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }

                // Social Links Section
                GlowingCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "Social Links",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonYellow
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            IconButton(onClick = onLinkedInClick) {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "LinkedIn",
                                    tint = NeonBlue
                                )
                            }
                            IconButton(onClick = onGitHubClick) {
                                Icon(
                                    Icons.Default.Code,
                                    contentDescription = "GitHub",
                                    tint = NeonPink
                                )
                            }
                            IconButton(onClick = onTwitterClick) {
                                Icon(
                                    Icons.Default.Share,
                                    contentDescription = "Twitter",
                                    tint = NeonGreen
                                )
                            }
                        }
                    }
                }
            }
        }
    }
} 