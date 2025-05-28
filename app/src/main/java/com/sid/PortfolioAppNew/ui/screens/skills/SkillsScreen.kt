package com.sid.PortfolioAppNew.ui.screens.skills

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sid.PortfolioAppNew.ui.theme.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.sid.PortfolioAppNew.ui.components.*

data class Skill(
    val name: String,
    val percentage: Int,
    val color: Color
)

enum class SkillCategory {
    PROGRAMMING_LANGUAGES,
    TOOLS_PLATFORMS,
    EXPERTISE,
    SOFT_SKILLS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillsScreen() {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Programming Languages
        SkillCategory(
            title = "Programming Languages",
            skills = listOf(
                Skill("Kotlin", 90, Color(0xFF00FFE7)),
                Skill("Java", 85, Color(0xFF7F00FF)),
                Skill("Python", 80, Color(0xFFFF4081)),
                Skill("C++", 75, Color(0xFFFFC107)),
                Skill("C", 70, Color(0xFF4CAF50)),
                Skill("HTML/CSS", 80, Color(0xFF03A9F4))
            )
        )
        
        // Tools & Platforms
        SkillCategory(
            title = "Tools & Platforms",
            skills = listOf(
                Skill("Android Studio", 95, Color(0xFF00FFE7)),
                Skill("Git", 90, Color(0xFF7F00FF)),
                Skill("Firebase", 85, Color(0xFFFF4081)),
                Skill("Docker", 75, Color(0xFFFFC107)),
                Skill("Jenkins", 70, Color(0xFF4CAF50))
            )
        )
        
        // Expertise
        SkillCategory(
            title = "Expertise",
            skills = listOf(
                Skill("Android Development", 95, Color(0xFF00FFE7)),
                Skill("Jetpack Compose", 90, Color(0xFF7F00FF)),
                Skill("Clean Architecture", 85, Color(0xFFFF4081)),
                Skill("MVVM", 90, Color(0xFFFFC107)),
                Skill("Unit Testing", 85, Color(0xFF4CAF50))
            )
        )
        
        // Soft Skills
        SkillCategory(
            title = "Soft Skills",
            skills = listOf(
                Skill("Problem Solving", 90, Color(0xFF00FFE7)),
                Skill("Team Leadership", 85, Color(0xFF7F00FF)),
                Skill("Communication", 90, Color(0xFFFF4081)),
                Skill("Project Management", 80, Color(0xFFFFC107))
            )
        )
        
        // Languages
        SkillCategory(
            title = "Languages",
            skills = listOf(
                Skill("English", 95, Color(0xFF00FFE7)),
                Skill("Hindi", 90, Color(0xFF7F00FF)),
                Skill("French", 70, Color(0xFFFF4081))
            )
        )
    }
}

@Composable
private fun SkillCategory(
    title: String,
    skills: List<Skill>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        
        skills.forEach { skill ->
            SkillProgressBar(skill)
        }
    }
}

@Composable
private fun SkillProgressBar(skill: Skill) {
    val progress by animateFloatAsState(
        targetValue = skill.percentage / 100f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "progress"
    )
    
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = skill.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = "${skill.percentage}%",
                style = MaterialTheme.typography.bodyMedium,
                color = skill.color
            )
        }
        
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(MaterialTheme.shapes.small),
            color = skill.color,
            trackColor = Color.DarkGray
        )
    }
} 