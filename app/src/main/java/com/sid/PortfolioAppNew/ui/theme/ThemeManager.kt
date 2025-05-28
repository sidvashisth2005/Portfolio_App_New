package com.sid.PortfolioAppNew.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.sid.PortfolioAppNew.utils.SettingsManager
import org.koin.compose.koinInject
import androidx.compose.runtime.SideEffect

val lightColorScheme = lightColorScheme(
    primary = Color(0xFF00FF00),
    secondary = Color(0xFF00FFFF),
    tertiary = Color(0xFFFF00FF),
    background = Color(0xFF000000),
    surface = Color(0xFF1A1A1A),
    onPrimary = Color(0xFF000000),
    onSecondary = Color(0xFF000000),
    onTertiary = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF)
)

val darkColorScheme = darkColorScheme(
    primary = Color(0xFF00FF00),
    secondary = Color(0xFF00FFFF),
    tertiary = Color(0xFFFF00FF),
    background = Color(0xFF000000),
    surface = Color(0xFF1A1A1A),
    onPrimary = Color(0xFF000000),
    onSecondary = Color(0xFF000000),
    onTertiary = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF)
)

@Composable
fun PortfolioTheme(
    settingsManager: SettingsManager = koinInject(),
    content: @Composable () -> Unit
) {
    val isDarkMode by settingsManager.darkMode.collectAsState(initial = false)
    val colorScheme = if (isDarkMode) darkColorScheme else lightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as android.app.Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkMode
        }
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
} 