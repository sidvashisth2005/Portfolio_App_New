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
    object ProjectDetail : Screen("project_detail/{projectId}") {
        fun createRoute(projectId: String) = "project_detail/$projectId"
    }
    object About : Screen("about")
    object Contact : Screen("contact")
    object Admin : Screen("admin")
    object Settings : Screen("settings")
    object Resume : Screen("resume")

    fun getIcon(): ImageVector {
        return when (this) {
            Home -> Icons.Outlined.Home
            Skills -> Icons.Outlined.Psychology
            Arx -> Icons.Outlined.ViewInAr
            Projects -> Icons.Outlined.Work
            About -> Icons.Outlined.Person
            Contact -> Icons.Outlined.ContactMail
            Admin -> Icons.Outlined.AdminPanelSettings
            Settings -> Icons.Outlined.Settings
            Resume -> Icons.Outlined.Description
            else -> Icons.Outlined.Home
        }
    }

    fun getSelectedIcon(): ImageVector {
        return when (this) {
            Home -> Icons.Filled.Home
            Skills -> Icons.Filled.Psychology
            Arx -> Icons.Filled.ViewInAr
            Projects -> Icons.Filled.Work
            About -> Icons.Filled.Person
            Contact -> Icons.Filled.ContactMail
            Admin -> Icons.Filled.AdminPanelSettings
            Settings -> Icons.Filled.Settings
            Resume -> Icons.Filled.Description
            else -> Icons.Filled.Home
        }
    }

    fun getLabel(): String {
        return when (this) {
            Home -> "Home"
            Skills -> "Skills"
            Arx -> "ARX"
            Projects -> "Projects"
            About -> "About"
            Contact -> "Contact"
            Admin -> "Admin"
            Settings -> "Settings"
            Resume -> "Resume"
            else -> "Home"
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