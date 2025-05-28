package com.sid.PortfolioAppNew.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Base colors
val Primary = Color(0xFFBB86FC)
val Secondary = Color(0xFF03DAC6)
val Tertiary = Color(0xFF3700B3)
val Error = Color(0xFFCF6679)
val DarkPrimary = Color(0xFF0066CC)

// On colors
val OnPrimary = Color(0xFF000000)
val OnSecondary = Color(0xFF000000)
val OnTertiary = Color(0xFFFFFFFF)
val OnBackground = Color(0xFFFFFFFF)
val OnSurface = Color(0xFFFFFFFF)

// Background colors
val LightBackground = Color(0xFFF5F5F5)
val LightSurface = Color(0xFFFFFFFF)
val DarkBackground = Color(0xFF121212)
val DarkSurface = Color(0xFF1E1E1E)
val NeonSurface = Color(0xFF1A1A1A)

// Neon theme colors
val NeonPrimary = Color(0xFF00FFE7)
val NeonSecondary = Color(0xFF7F00FF)
val NeonTertiary = Color(0xFFFF00FF)
val NeonBlue = Color(0xFF00F3FF)
val NeonPink = Color(0xFFFF00FF)
val NeonPurple = Color(0xFF9D00FF)
val NeonGreen = Color(0xFF00FF00)
val NeonYellow = Color(0xFFFFE600)
val NeonCyan = Color(0xFF00FFE7)
val NeonOrange = Color(0xFFFF6B00)
val NeonRed = Color(0xFFFF0000)
val NeonError = Color(0xFFFF1744)

// Text colors
val TextBlack = Color(0xFF000000)
val TextWhite = Color(0xFFFFFFFF)
val NeonTextPrimary = Color(0xFFFFFFFF)
val NeonTextSecondary = Color(0xB3FFFFFF)
val NeonTextTertiary = Color(0x80FFFFFF)

// Status colors
val NeonSuccess = Color(0xFF00E676)
val NeonWarning = Color(0xFFFFD600)
val NeonInfo = Color(0xFF00B0FF)

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary,
    background = DarkBackground,
    surface = DarkSurface,
    error = Error,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onTertiary = OnTertiary,
    onBackground = OnBackground,
    onSurface = OnSurface
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary,
    background = LightBackground,
    surface = LightSurface,
    error = Error,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onTertiary = OnTertiary,
    onBackground = OnBackground,
    onSurface = OnSurface
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
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}