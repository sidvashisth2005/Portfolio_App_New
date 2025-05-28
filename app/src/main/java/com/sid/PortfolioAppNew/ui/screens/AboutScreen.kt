package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onNavigateBack: () -> Unit
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
                title = { Text("About", style = MaterialTheme.typography.headlineMedium) },
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
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Profile Section
                GlowingCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "SID",
                            style = MaterialTheme.typography.headlineLarge,
                            color = NeonBlue
                        )
                        Text(
                            "Android Developer",
                            style = MaterialTheme.typography.titleMedium,
                            color = NeonPink
                        )
                    }
                }

                // Bio Section
                GlowingCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "Bio",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonGreen
                        )
                        Text(
                            "Passionate Android developer with expertise in modern Android development technologies. " +
                            "Specializing in Jetpack Compose, Kotlin, and creating beautiful, performant applications.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                // Experience Section
                GlowingCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "Experience",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonPurple
                        )
                        Text(
                            "• Senior Android Developer\n" +
                            "• Mobile App Architect\n" +
                            "• UI/UX Enthusiast",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                // Education Section
                GlowingCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "Education",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonYellow
                        )
                        Text(
                            "• B.S. in Computer Science\n" +
                            "• Advanced Android Development Certification\n" +
                            "• Google Developer Expert",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
} 