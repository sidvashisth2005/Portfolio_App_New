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
fun ARScreen(
    onNavigateBack: () -> Unit,
    highQualityAR: Boolean
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
                title = { Text("AR Experience", style = MaterialTheme.typography.headlineMedium) },
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                GlowingCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "AR Experience",
                            style = MaterialTheme.typography.headlineMedium,
                            color = NeonBlue
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "Coming Soon",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonPink
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Quality Mode: ${if (highQualityAR) "High" else "Standard"}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = NeonGreen
                        )
                    }
                }
            }
        }
    }
} 