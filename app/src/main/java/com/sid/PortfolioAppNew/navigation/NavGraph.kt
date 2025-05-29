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
import com.sid.PortfolioAppNew.ui.screens.projects.ProjectDetailScreen
import com.sid.PortfolioAppNew.ui.screens.projects.ProjectsScreen
import com.sid.PortfolioAppNew.ui.screens.skills.SkillsScreen
import com.sid.PortfolioAppNew.ui.screens.ar.ARScreen
import com.sid.PortfolioAppNew.ui.screens.settings.SettingsScreen
import com.sid.PortfolioAppNew.utils.SettingsManager
import androidx.compose.ui.Modifier
import dagger.hilt.android.EntryPointAccessors
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

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
        composable(Screen.Skills.route) {
            SkillsScreen()
        }
        composable(Screen.Arx.route) {
            ARScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Projects.route) {
            ProjectsScreen(
                onNavigateBack = { navController.popBackStack() },
                onProjectClick = { projectId ->
                    navController.navigate(Screen.ProjectDetail.createRoute(projectId))
                }
            )
        }
        composable(Screen.ProjectDetail.route) { backStackEntry ->
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
        composable(Screen.Contact.route) {
            // TODO: Implement ContactScreen
        }
        composable(Screen.Admin.route) {
            // TODO: Implement AdminScreen
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                settingsManager = settingsManager
            )
        }
        composable(Screen.Resume.route) {
            // TODO: Implement ResumeScreen
        }
    }
}

@dagger.hilt.EntryPoint
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
interface SettingsEntryPoint {
    fun settingsManager(): SettingsManager
} 
