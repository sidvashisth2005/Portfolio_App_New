package com.sid.PortfolioAppNew.ui.screens.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sid.PortfolioAppNew.ui.theme.*

data class Project(
    val title: String,
    val description: String,
    val technologies: List<String>,
    val githubUrl: String? = null,
    val playStoreUrl: String? = null
)

@Composable
fun ProjectsScreen() {
    val projects = remember {
        listOf(
            Project(
                title = "Portfolio App",
                description = "A modern Android portfolio app showcasing my work and skills",
                technologies = listOf("Kotlin", "Jetpack Compose", "Material3", "AR")
            ),
            Project(
                title = "AR Experience",
                description = "An immersive AR application for interactive learning",
                technologies = listOf("ARCore", "Kotlin", "Sceneform")
            ),
            Project(
                title = "Task Manager",
                description = "A productivity app with advanced task management features",
                technologies = listOf("Kotlin", "Room", "Coroutines", "MVVM")
            ),
            Project(
                title = "Weather App",
                description = "Real-time weather updates with beautiful UI",
                technologies = listOf("Kotlin", "Retrofit", "Hilt", "Compose")
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Projects",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(projects) { project ->
                    ProjectCard(project = project)
                }
            }
        }
    }
}

@Composable
private fun ProjectCard(project: Project) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A1A)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = project.title,
                color = NeonBlue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = project.description,
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                project.technologies.forEach { tech ->
                    TechChip(tech = tech)
                }
            }
        }
    }
}

@Composable
private fun TechChip(tech: String) {
    Surface(
        modifier = Modifier,
        color = NeonBlue.copy(alpha = 0.2f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = tech,
            color = NeonBlue,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
} 