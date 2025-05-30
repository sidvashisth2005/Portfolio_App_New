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
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.R

enum class SkillCategory {
    PROGRAMMING_LANGUAGES,
    TOOLS_PLATFORMS,
    EXPERTISE,
    SOFT_SKILLS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillsScreen(
    onNavigateBack: () -> Unit = {},
    viewModel: SkillsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Lottie animation for header
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.skills_animation)
        )
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )
        
        Text(
            text = "My Skills",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        when (uiState) {
            is SkillsUiState.Loading -> {
                CircularProgressIndicator()
            }
            is SkillsUiState.Success -> {
                val skills = (uiState as SkillsUiState.Success).skills
                SkillsByCategory(skills = skills)
            }
            is SkillsUiState.Error -> {
                Text(
                    text = (uiState as SkillsUiState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun SkillsByCategory(skills: List<Skill>) {
    val grouped = skills.groupBy { it.category }
    val lottieMap = mapOf(
        "Programming Languages" to R.raw.skills_animation,
        "Frameworks & Libraries" to R.raw.skills_animation,
        "Tools & Platforms" to R.raw.skills_animation,
        "Soft Skills" to R.raw.skills_animation
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        grouped.forEach { (category, skillsInCategory) ->
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Optional: Lottie animation above each category
                lottieMap[category]?.let { lottieRes ->
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))
                    val progress by animateLottieCompositionAsState(
                        composition = composition,
                        iterations = LottieConstants.IterateForever
                    )
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    skillsInCategory.forEach { skill ->
                        AnimatedSkillBar(skill)
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimatedSkillBar(skill: Skill) {
    val progress by animateFloatAsState(
        targetValue = skill.level / 100f,
        animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
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
                text = "${skill.level}%",
                style = MaterialTheme.typography.bodyMedium,
                color = skill.color
            )
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(MaterialTheme.shapes.small),
            color = skill.color,
            trackColor = Color.DarkGray,
            progress = { progress }
        )
    }
} 