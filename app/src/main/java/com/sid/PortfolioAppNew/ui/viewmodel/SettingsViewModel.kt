package com.sid.PortfolioAppNew.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid.PortfolioAppNew.utils.SettingsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsManager: SettingsManager) : ViewModel() {
    private val _isDarkTheme = MutableStateFlow(true)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    private val _notificationsEnabled = MutableStateFlow(true)
    val notificationsEnabled: StateFlow<Boolean> = _notificationsEnabled.asStateFlow()

    private val _highQualityAR = MutableStateFlow(false)
    val highQualityAR: StateFlow<Boolean> = _highQualityAR.asStateFlow()

    init {
        viewModelScope.launch {
            settingsManager.isDarkTheme.collect { isDark ->
                _isDarkTheme.value = isDark
            }
        }
        viewModelScope.launch {
            settingsManager.notificationsEnabled.collect { enabled ->
                _notificationsEnabled.value = enabled
            }
        }
        viewModelScope.launch {
            settingsManager.highQualityAR.collect { enabled ->
                _highQualityAR.value = enabled
            }
        }
    }

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            settingsManager.setDarkTheme(enabled)
        }
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsManager.setNotificationsEnabled(enabled)
        }
    }

    fun setHighQualityAR(enabled: Boolean) {
        viewModelScope.launch {
            settingsManager.setHighQualityAR(enabled)
        }
    }

    fun toggleDarkMode() {
        _isDarkTheme.value = !_isDarkTheme.value
    }

    fun toggleNotifications() {
        _notificationsEnabled.value = !_notificationsEnabled.value
    }
} 