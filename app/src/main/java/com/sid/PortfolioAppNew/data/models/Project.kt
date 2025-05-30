package com.sid.PortfolioAppNew.data.models

data class Project(
    val id: String = "",
    val title: String = "",
    val shortDesc: String = "",
    val longDesc: String = "",
    val previewImageUrl: String = "",
    val images: List<String> = emptyList(),
    val githubLink: String = "",
    val demoLink: String = "",
    val technologies: List<String> = emptyList(),
    val category: ProjectCategory = ProjectCategory.OTHER,
    val status: ProjectStatus = ProjectStatus.PLANNED,
    val colorPalette: ProjectColorPalette = ProjectColorPalette.OTHER
)


enum class ProjectColorPalette {
    ANDROID,
    WEB,
    GAME,
    AR,
    AI,
    OTHER
} 