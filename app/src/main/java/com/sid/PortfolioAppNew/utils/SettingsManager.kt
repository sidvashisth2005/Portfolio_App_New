package com.sid.PortfolioAppNew.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsManager(private val context: Context) : KoinComponent {
    private val isDarkThemeKey = booleanPreferencesKey("is_dark_theme")
    private val notificationsEnabledKey = booleanPreferencesKey("notifications_enabled")
    private val highQualityARKey = booleanPreferencesKey("high_quality_ar")

    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[isDarkThemeKey] ?: false
        }

    val notificationsEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[notificationsEnabledKey] ?: true
        }

    val highQualityAR: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[highQualityARKey] ?: false
        }

    suspend fun setDarkTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[isDarkThemeKey] = isDark
        }
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[notificationsEnabledKey] = enabled
        }
    }

    suspend fun setHighQualityAR(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[highQualityARKey] = enabled
        }
    }
} 