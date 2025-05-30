package com.sid.PortfolioAppNew.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sid.PortfolioAppNew.ui.screens.home.HomeScreen
import com.sid.PortfolioAppNew.ui.screens.projects.ProjectsScreen
import com.sid.PortfolioAppNew.ui.screens.projects.ProjectDetailScreen
import com.sid.PortfolioAppNew.ui.screens.about.AboutScreen
import androidx.compose.ui.Modifier
import dagger.hilt.android.EntryPointAccessors
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sid.PortfolioAppNew.utils.SettingsManager

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PortfolioNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val settingsManager = remember {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            SettingsEntryPoint::class.java
        ).settingsManager()
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Projects.route) {
            ProjectsScreen(
                onNavigateBack = { navController.popBackStack() },
                onProjectClick = { projectId ->
                    navController.navigate("${Screen.ProjectDetail.route}/$projectId")
                }
            )
        }
        composable(Screen.ProjectDetail.route + "/{projectId}") { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: ""
            ProjectDetailScreen(
                projectId = projectId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.About.route) {
            AboutScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

@dagger.hilt.EntryPoint
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
interface SettingsEntryPoint {
    fun settingsManager(): SettingsManager
} 
