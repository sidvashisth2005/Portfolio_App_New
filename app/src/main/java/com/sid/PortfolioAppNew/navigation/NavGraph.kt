package com.sid.PortfolioAppNew.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sid.PortfolioAppNew.ui.screens.about.AboutScreen
import com.sid.PortfolioAppNew.ui.screens.arx.ArxScreen
import com.sid.PortfolioAppNew.ui.screens.home.HomeScreen
import com.sid.PortfolioAppNew.ui.screens.projects.ProjectsScreen
import com.sid.PortfolioAppNew.ui.screens.skills.SkillsScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PortfolioNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Skills.route) {
            SkillsScreen()
        }
        composable(Screen.Arx.route) {
            ArxScreen()
        }
        composable(Screen.Projects.route) {
            ProjectsScreen()
        }
        composable(Screen.About.route) {
            AboutScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
} 