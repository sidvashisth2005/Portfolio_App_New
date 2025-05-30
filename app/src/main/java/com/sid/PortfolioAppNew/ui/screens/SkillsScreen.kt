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
fun SkillsScreen(
    onNavigateBack: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var skills by remember { mutableStateOf<List<Skill>>(emptyList()) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1500)
        isLoading = false
        skills = skillCategories.flatMap { it.skills }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        AnimatedLottieBackground(
            modifier = Modifier.fillMaxSize(),
            lottieResId = Icons.techBackground,
            alpha = 0.2f
        )

        AnimatedPageTransition {
            when {
                isLoading -> {
                    LoadingAnimation(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                skills.isEmpty() -> {
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
                            text = "No skills yet!",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                        Text(
                            text = "Stay tuned for upcoming skills",
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
                        items(skillCategories) { category ->
                            AnimatedSkillCard(
                                title = category.name,
                                icon = category.icon
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    category.skills.forEach { skill ->
                                        AnimatedSkillItem(
                                            name = skill.name,
                                            icon = skill.icon,
                                            level = skill.level
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
private fun AnimatedSkillItem(
    name: String,
    icon: Int,
    level: Int
) {
    var isHovered by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.05f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable { isHovered = !isHovered },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CyberpunkLottieAnimation(
            modifier = Modifier.size(32.dp),
            lottieResId = icon
        )
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            LinearProgressIndicator(
                progress = { level / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = NeonBlue,
                trackColor = Color.White.copy(alpha = 0.1f)
            )
        }
    }
}

private data class SkillCategory(
    val name: String,
    val icon: Int,
    val skills: List<Skill>
)

private data class Skill(
    val name: String,
    val icon: Int,
    val level: Int
)

private val skillCategories = listOf(
    SkillCategory(
        name = "Android Development",
        icon = Icons.androidDevelopment,
        skills = listOf(
            Skill("Kotlin", Icons.kotlinIcon, 90),
            Skill("Jetpack Compose", Icons.composeIcon, 85),
            Skill("Android SDK", Icons.androidSdkIcon, 88),
            Skill("Material Design", Icons.materialDesignIcon, 82)
        )
    ),
    SkillCategory(
        name = "AR/VR Development",
        icon = Icons.arVrDevelopment,
        skills = listOf(
            Skill("ARCore", Icons.arcoreIcon, 85),
            Skill("Unity", Icons.unityIcon, 80),
            Skill("3D Modeling", Icons.modelingIcon, 75),
            Skill("Spatial Computing", Icons.spatialIcon, 82)
        )
    ),
    SkillCategory(
        name = "Backend Development",
        icon = Icons.backendDevelopment,
        skills = listOf(
            Skill("Firebase", Icons.firebaseIcon, 88),
            Skill("Node.js", Icons.nodejsIcon, 85),
            Skill("REST APIs", Icons.apiIcon, 90),
            Skill("GraphQL", Icons.graphqlIcon, 80)
        )
    ),
    SkillCategory(
        name = "Tools & Practices",
        icon = Icons.toolsIcon,
        skills = listOf(
            Skill("Git", Icons.gitIcon, 92),
            Skill("Docker", Icons.dockerIcon, 78),
            Skill("CI/CD", Icons.cicdIcon, 85),
            Skill("Agile", Icons.agileIcon, 88)
        )
    )
) 