package com.sid.PortfolioAppNew.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object Achievements : Screen("achievements", Icons.Default.EmojiEvents, "Home")
    object Skills : Screen("skills", Icons.Default.Timeline, "Skills")
    object ARX : Screen("arx", Icons.Default.ViewInAr, "ARX")
    object Projects : Screen("projects", Icons.Default.Folder, "Projects")
    object About : Screen("about", Icons.Default.Person, "About")

    companion object {
        val bottomNavItems = listOf(
            Achievements,
            Skills,
            ARX,
            Projects,
            About
        )
    }
} 