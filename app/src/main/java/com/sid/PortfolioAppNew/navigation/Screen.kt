package com.sid.PortfolioAppNew.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Skills : Screen("skills")
    object ARX : Screen("arx")
    object Projects : Screen("projects")
    object About : Screen("about")
    object ProjectDetail : Screen("project_detail")

    fun getIcon(): ImageVector {
        return when (this) {
            Home -> Icons.Outlined.Home
            Skills -> Icons.Outlined.Code
            ARX -> Icons.Outlined.ViewInAr
            Projects -> Icons.Outlined.Work
            About -> Icons.Outlined.Person
            else -> Icons.Outlined.Home
        }
    }

    fun getSelectedIcon(): ImageVector {
        return when (this) {
            Home -> Icons.Filled.Home
            Skills -> Icons.Filled.Code
            ARX -> Icons.Filled.ViewInAr
            Projects -> Icons.Filled.Work
            About -> Icons.Filled.Person
            else -> Icons.Filled.Home
        }
    }

    fun getLabel(): String {
        return when (this) {
            Home -> "Home"
            Skills -> "Skills"
            ARX -> "ARX"
            Projects -> "Projects"
            About -> "About"
            else -> "Home"
        }
    }
     
    companion object {
        val bottomNavItems = listOf(
            Home,
            Skills,
            ARX,
            Projects,
            About
        )
    }
} 