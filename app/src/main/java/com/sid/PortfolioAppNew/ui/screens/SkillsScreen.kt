package com.sid.PortfolioAppNew.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sid.PortfolioAppNew.ui.components.AnimatedSkillChip

@Composable
fun SkillsScreen() {
    val skills = listOf(
        "Java", "Kotlin", "Android", "Jetpack Compose",
        "AR/VR", "Unity", "Firebase", "Git",
        "Material Design", "REST APIs", "MVVM", "Clean Architecture"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Technical Skills",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(skills) { skill ->
            AnimatedSkillChip(skill)
        }
    }
} 