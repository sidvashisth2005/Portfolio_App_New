package com.sid.PortfolioAppNew.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Projects : Screen("projects")
    object About : Screen("about")
    object ProjectDetail : Screen("project_detail")

    fun getIcon(): ImageVector {
        return when (this) {
            Home -> Icons.Outlined.Home
            Projects -> Icons.Outlined.Work
            About -> Icons.Outlined.Person
            else -> Icons.Outlined.Home
        }
    }

    fun getSelectedIcon(): ImageVector {
        return when (this) {
            Home -> Icons.Filled.Home
            Projects -> Icons.Filled.Work
            About -> Icons.Filled.Person
            else -> Icons.Filled.Home
        }
    }

    fun getLabel(): String {
        return when (this) {
            Home -> "Home"
            Projects -> "Projects"
            About -> "About"
            else -> "Home"
        }
    }
     
    companion object {
        val bottomNavItems = listOf(
            Home,
            Projects,
            About
        )
    }
} 