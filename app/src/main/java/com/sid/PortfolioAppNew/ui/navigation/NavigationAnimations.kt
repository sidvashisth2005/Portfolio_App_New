package com.sid.PortfolioAppNew.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object NavigationAnimations {
    @OptIn(ExperimentalAnimationApi::class)
    fun enterTransition() = slideInHorizontally(
        initialOffsetX = { 1000 },
        animationSpec = tween(300)
    ) + fadeIn(animationSpec = tween(300))

    @OptIn(ExperimentalAnimationApi::class)
    fun exitTransition() = slideOutHorizontally(
        targetOffsetX = { -1000 },
        animationSpec = tween(300)
    ) + fadeOut(animationSpec = tween(300))

    @OptIn(ExperimentalAnimationApi::class)
    fun popEnterTransition() = slideInHorizontally(
        initialOffsetX = { -1000 },
        animationSpec = tween(300)
    ) + fadeIn(animationSpec = tween(300))

    @OptIn(ExperimentalAnimationApi::class)
    fun popExitTransition() = slideOutHorizontally(
        targetOffsetX = { 1000 },
        animationSpec = tween(300)
    ) + fadeOut(animationSpec = tween(300))
}

@Composable
fun AnimatedPageTransition(
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
        content = content
    )
} 