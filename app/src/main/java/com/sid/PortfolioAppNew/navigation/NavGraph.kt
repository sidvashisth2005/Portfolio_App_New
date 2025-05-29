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
import com.sid.PortfolioAppNew.ui.screens.ar.ARScreen
import com.sid.PortfolioAppNew.ui.screens.settings.SettingsScreen
import com.sid.PortfolioAppNew.utils.SettingsManager
import androidx.compose.ui.Modifier
import dagger.hilt.android.EntryPointAccessors
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

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
                    navController.navigate("project_detail/$projectId")
                }
            )
        }
        composable(Screen.About.route) {
            AboutScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable("settings") {
            SettingsScreen(
                settingsManager = settingsManager
            )
        }
    }
}

@dagger.hilt.EntryPoint
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
interface SettingsEntryPoint {
    fun settingsManager(): SettingsManager
} 
