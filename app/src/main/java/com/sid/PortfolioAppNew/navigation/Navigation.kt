package com.sid.PortfolioAppNew.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sid.PortfolioAppNew.ui.navigation.NavigationAnimations
import com.sid.PortfolioAppNew.ui.screens.home.HomeScreen
import com.sid.PortfolioAppNew.ui.screens.projects.ProjectsScreen
import com.sid.PortfolioAppNew.ui.screens.projects.ProjectDetailScreen
import com.sid.PortfolioAppNew.ui.screens.about.AboutScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = { NavigationAnimations.enterTransition() },
            exitTransition = { NavigationAnimations.exitTransition() },
            popEnterTransition = { NavigationAnimations.popEnterTransition() },
            popExitTransition = { NavigationAnimations.popExitTransition() }
        ) {
            HomeScreen()
        }

        composable(
            route = Screen.Projects.route,
            enterTransition = { NavigationAnimations.enterTransition() },
            exitTransition = { NavigationAnimations.exitTransition() },
            popEnterTransition = { NavigationAnimations.popEnterTransition() },
            popExitTransition = { NavigationAnimations.popExitTransition() }
        ) {
            ProjectsScreen(
                onNavigateBack = { navController.popBackStack() },
                onProjectClick = { projectId ->
                    navController.navigate("${Screen.ProjectDetail.route}/$projectId")
                }
            )
        }

        composable(
            route = Screen.ProjectDetail.route + "/{projectId}",
            enterTransition = { NavigationAnimations.enterTransition() },
            exitTransition = { NavigationAnimations.exitTransition() },
            popEnterTransition = { NavigationAnimations.popEnterTransition() },
            popExitTransition = { NavigationAnimations.popExitTransition() }
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: return@composable
            ProjectDetailScreen(
                projectId = projectId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.About.route,
            enterTransition = { NavigationAnimations.enterTransition() },
            exitTransition = { NavigationAnimations.exitTransition() },
            popEnterTransition = { NavigationAnimations.popEnterTransition() },
            popExitTransition = { NavigationAnimations.popExitTransition() }
        ) {
            AboutScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
} 