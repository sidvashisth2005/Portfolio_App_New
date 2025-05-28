package com.sid.PortfolioAppNew.ui.screens.skills

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.sid.PortfolioAppNew.ui.components.NeonText
import com.sid.PortfolioAppNew.ui.theme.*
import com.sid.PortfolioAppNew.ui.viewmodel.PortfolioViewModel

data class Skill(
    val name: String,
    val level: Float,
    val color: Color
)

@Composable
fun SkillsScreen() {
    val skills = remember {
        listOf(
            Skill("Kotlin", 0.9f, NeonBlue),
            Skill("Android", 0.85f, NeonPink),
            Skill("Jetpack Compose", 0.8f, NeonGreen),
            Skill("Material Design", 0.75f, NeonPurple),
            Skill("AR Development", 0.7f, NeonCyan),
            Skill("Firebase", 0.8f, NeonOrange),
            Skill("Git", 0.85f, NeonRed)
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
                text = "My Skills",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(skills) { index, skill ->
                    SkillCard(skill = skill, index = index)
                }
            }
        }
    }
}

@Composable
private fun SkillCard(skill: Skill, index: Int) {
    val infiniteTransition = rememberInfiniteTransition(label = "skill")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = skill.level,
        animationSpec = infiniteRepeatable(
            animation = tween(1000 + (index * 200)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "progress"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
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
                text = skill.name,
                color = skill.color,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF2A2A2A))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    skill.color.copy(alpha = 0.7f),
                                    skill.color
                                )
                            )
                        )
                )
            }
        }
    }
} 