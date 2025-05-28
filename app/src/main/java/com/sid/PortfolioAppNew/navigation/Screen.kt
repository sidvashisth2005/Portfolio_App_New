package com.sid.PortfolioAppNew.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Skills : Screen("skills")
    object Arx : Screen("arx")
    object Projects : Screen("projects")
    object About : Screen("about")

    fun getIcon(): ImageVector {
        return when (this) {
            Home -> Icons.Outlined.Home
            Skills -> Icons.Outlined.Psychology
            Arx -> Icons.Outlined.ViewInAr
            Projects -> Icons.Outlined.Work
            About -> Icons.Outlined.Person
        }
    }

    fun getSelectedIcon(): ImageVector {
        return when (this) {
            Home -> Icons.Filled.Home
            Skills -> Icons.Filled.Psychology
            Arx -> Icons.Filled.ViewInAr
            Projects -> Icons.Filled.Work
            About -> Icons.Filled.Person
        }
    }

    fun getLabel(): String {
        return when (this) {
            Home -> "Home"
            Skills -> "Skills"
            Arx -> "ARX"
            Projects -> "Projects"
            About -> "About"
        }
    }

    companion object {
        val bottomNavItems = listOf(
            Home,
            Skills,
            Arx,
            Projects,
            About
        )
    }
} 