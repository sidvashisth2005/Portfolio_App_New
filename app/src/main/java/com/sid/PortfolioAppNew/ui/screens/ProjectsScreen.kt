package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Project(
    val title: String,
    val description: String,
    val technologies: List<String>
)

@Composable
fun ProjectsScreen() {
    val projects = listOf(
        Project(
            title = "Portfolio App",
            description = "A modern Android portfolio app built with Jetpack Compose",
            technologies = listOf("Kotlin", "Jetpack Compose", "Material3")
        ),
        Project(
            title = "AR Experience",
            description = "An augmented reality application for interactive experiences",
            technologies = listOf("Unity", "ARCore", "C#")
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Projects",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(projects) { project ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = project.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        project.technologies.forEach { tech ->
                            AssistChip(
                                onClick = { },
                                label = { Text(tech) }
                            )
                        }
                    }
                }
            }
        }
    }
} 