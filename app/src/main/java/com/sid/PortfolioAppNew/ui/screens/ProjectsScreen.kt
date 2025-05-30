package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.R
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*
import com.sid.PortfolioAppNew.ui.theme.Icons

@Composable
fun ProjectsScreen(
    onNavigateBack: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var projects by remember { mutableStateOf<List<Project>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Simulate loading
        kotlinx.coroutines.delay(1500)
        isLoading = false
        // TODO: Replace with actual project loading
        projects = projectCategories.flatMap { it.projects }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        // Background animation
        AnimatedLottieBackground(
            modifier = Modifier.fillMaxSize(),
            lottieResId = R.raw.code_background,
            alpha = 0.2f
        )

        AnimatedPageTransition {
            when {
                isLoading -> {
                    LoadingAnimation(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                projects.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        EmptyStateAnimation(
                            modifier = Modifier.size(200.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No projects yet!",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                        Text(
                            text = "Stay tuned for upcoming projects",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Featured Project
                        item {
                            AnimatedProjectCard(
                                title = "AR Navigation App",
                                description = "An augmented reality navigation app that overlays directions on the real world",
                                icon = Icons.arNavigationIcon,
                                technologies = listOf(
                                    "ARCore" to Icons.arcoreIcon,
                                    "Kotlin" to Icons.kotlinIcon,
                                    "Google Maps" to Icons.mapsIcon
                                ),
                                onClick = { /* Navigate to project details */ }
                            )
                        }

                        // Project Categories
                        items(projectCategories) { category ->
                            AnimatedSkillCard(
                                title = category.name,
                                icon = category.icon
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    category.projects.forEach { project ->
                                        AnimatedProjectItem(
                                            name = project.name,
                                            description = project.description,
                                            icon = project.icon,
                                            technologies = project.technologies
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimatedProjectItem(
    name: String,
    description: String,
    icon: Int,
    technologies: List<Pair<String, Int>>
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

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable { isHovered = !isHovered },
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
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

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

private data class ProjectCategory(
    val name: String,
    val icon: Int,
    val projects: List<Project>
)

private data class Project(
    val name: String,
    val description: String,
    val icon: Int,
    val technologies: List<Pair<String, Int>>
)

private val projectCategories = listOf(
    ProjectCategory(
        name = "AR/VR Projects",
        icon = R.raw.ar_vr_development,
        projects = listOf(
            Project(
                name = "VR Art Gallery",
                description = "A virtual reality art gallery with interactive 3D models",
                icon = R.raw.vr_gallery_icon,
                technologies = listOf(
                    "Unity" to R.raw.unity_icon,
                    "C#" to R.raw.csharp_icon,
                    "3D Modeling" to R.raw.modeling_icon
                )
            ),
            Project(
                name = "AR Model Viewer",
                description = "View and interact with 3D models in augmented reality",
                icon = R.raw.model_viewer_icon,
                technologies = listOf(
                    "ARCore" to R.raw.arcore_icon,
                    "Kotlin" to R.raw.kotlin_icon,
                    "OpenGL" to R.raw.opengl_icon
                )
            )
        )
    ),
    ProjectCategory(
        name = "Android Apps",
        icon = R.raw.android_development,
        projects = listOf(
            Project(
                name = "Portfolio App",
                description = "A modern portfolio app showcasing my work and skills",
                icon = R.raw.portfolio_icon,
                technologies = listOf(
                    "Jetpack Compose" to R.raw.compose_icon,
                    "Kotlin" to R.raw.kotlin_icon,
                    "Material Design" to R.raw.material_design_icon
                )
            ),
            Project(
                name = "Task Manager",
                description = "A productivity app with task management and reminders",
                icon = R.raw.task_manager_icon,
                technologies = listOf(
                    "MVVM" to R.raw.mvvm_icon,
                    "Room" to R.raw.room_icon,
                    "Coroutines" to R.raw.coroutines_icon
                )
            )
        )
    ),
    ProjectCategory(
        name = "Web Development",
        icon = R.raw.web_development,
        projects = listOf(
            Project(
                name = "E-commerce Platform",
                description = "A full-stack e-commerce platform with real-time inventory",
                icon = R.raw.ecommerce_icon,
                technologies = listOf(
                    "React" to R.raw.react_icon,
                    "Node.js" to R.raw.nodejs_icon,
                    "MongoDB" to R.raw.mongodb_icon
                )
            ),
            Project(
                name = "Tech Blog",
                description = "A blog platform for sharing tech articles and tutorials",
                icon = R.raw.blog_icon,
                technologies = listOf(
                    "Next.js" to R.raw.nextjs_icon,
                    "GraphQL" to R.raw.graphql_icon,
                    "PostgreSQL" to R.raw.postgresql_icon
                )
            )
        )
    )
) 