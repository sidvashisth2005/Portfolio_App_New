package com.sid.PortfolioAppNew.data.models

data class Project(
    val id: String,
    val title: String,
    val shortDesc: String,
    val longDesc: String,
    val images: List<String>,
    val githubLink: String,
    val demoLink: String,
    val technologies: List<String>,
    val category: ProjectCategory,
    val status: ProjectStatus,
    val colorPalette: ProjectColorPalette
)

enum class ProjectColorPalette {
    ANDROID,
    WEB,
    GAME,
    AR,
    AI,
    OTHER
} 