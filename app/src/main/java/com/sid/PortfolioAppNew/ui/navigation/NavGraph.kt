package com.sid.PortfolioAppNew.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sid.PortfolioAppNew.ui.screens.about.AboutScreen
import com.sid.PortfolioAppNew.ui.screens.arx.ArxScreen
import com.sid.PortfolioAppNew.ui.screens.home.HomeScreen
import com.sid.PortfolioAppNew.ui.screens.projects.ProjectsScreen
import com.sid.PortfolioAppNew.ui.screens.skills.SkillsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen()
        }
        composable("skills") {
            SkillsScreen()
        }
        composable("arx") {
            ArxScreen()
        }
        composable("projects") {
            ProjectsScreen()
        }
        composable("about") {
            AboutScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("contact") {
            // TODO: Implement ContactScreen
        }
        composable("admin") {
            // TODO: Implement AdminScreen
        }
        composable("settings") {
            // TODO: Implement SettingsScreen
        }
        composable("resume") {
            // TODO: Implement ResumeScreen
        }
    }
} 