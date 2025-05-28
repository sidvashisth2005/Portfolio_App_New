package com.sid.PortfolioAppNew.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Neon theme colors
val NeonPrimary = Color(0xFF00FFE7)
val NeonBlue = Color(0xFF00BFFF)
val NeonPink = Color(0xFFFF1493)
val NeonPurple = Color(0xFF7F00FF)
val NeonGreen = Color(0xFF00FF00)
val NeonYellow = Color(0xFFFFFF00)
val NeonOrange = Color(0xFFFFA500)

// Dark theme colors
val DarkBackground = Color(0xFF121212)
val DarkSurface = Color(0xFF1A1A1A)
val DarkPrimary = Color(0xFF2A2A2A)
val DarkSecondary = Color(0xFF3A3A3A)

// Error colors
val NeonError = Color(0xFFFF1744)

private val DarkColorScheme = darkColorScheme(
    primary = NeonPrimary,
    secondary = NeonBlue,
    tertiary = NeonPurple,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    error = NeonError
)

@Composable
fun PortfolioAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> DarkColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}