package com.sid.PortfolioAppNew.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sid.PortfolioAppNew.ui.viewmodel.SettingsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ThemeManager(
    content: @Composable () -> Unit
) {
    val settingsViewModel: SettingsViewModel = getViewModel()
    val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState(initial = true)

    PortfolioAppTheme(isDarkTheme = isDarkTheme) {
        content()
    }
} 