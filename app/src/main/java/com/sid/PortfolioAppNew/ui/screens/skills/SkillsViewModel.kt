package com.sid.PortfolioAppNew.ui.screens.skills

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.compose.ui.graphics.Color

sealed class SkillsUiState {
    object Loading : SkillsUiState()
    data class Success(val skills: List<Skill>) : SkillsUiState()
    data class Error(val message: String) : SkillsUiState()
}

data class Skill(
    val name: String,
    val level: Int,
    val color: Color,
    val category: String
)

@HiltViewModel
class SkillsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<SkillsUiState>(SkillsUiState.Loading)
    val uiState: StateFlow<SkillsUiState> = _uiState

    init {
        loadSkills()
    }

    private fun loadSkills() {
        val skills = listOf(
            // Programming Languages
            Skill("Kotlin", 90, Color(0xFF00BCD4), "Programming Languages"),
            Skill("Java", 85, Color(0xFF2196F3), "Programming Languages"),
            Skill("Python", 80, Color(0xFF4CAF50), "Programming Languages"),
            // Frameworks & Libraries
            Skill("Jetpack Compose", 90, Color(0xFF9C27B0), "Frameworks & Libraries"),
            Skill("Android SDK", 88, Color(0xFF3F51B5), "Frameworks & Libraries"),
            Skill("Material Design", 82, Color(0xFFFFB300), "Frameworks & Libraries"),
            // Tools & Platforms
            Skill("Git", 90, Color(0xFF607D8B), "Tools & Platforms"),
            Skill("Firebase", 85, Color(0xFFFFC107), "Tools & Platforms"),
            Skill("Docker", 78, Color(0xFF0db7ed), "Tools & Platforms"),
            Skill("CI/CD", 85, Color(0xFF795548), "Tools & Platforms"),
            // Soft Skills
            Skill("Teamwork", 92, Color(0xFF8BC34A), "Soft Skills"),
            Skill("Communication", 88, Color(0xFFE91E63), "Soft Skills"),
            Skill("Problem Solving", 90, Color(0xFFFF9800), "Soft Skills"),
            Skill("Leadership", 85, Color(0xFF3F51B5), "Soft Skills")
        )
        _uiState.value = SkillsUiState.Success(skills)
    }
} 