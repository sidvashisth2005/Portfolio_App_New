package com.sid.PortfolioAppNew.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sid.PortfolioAppNew.utils.SettingsManager

@Composable
fun SettingsScreen(
    settingsManager: SettingsManager,
    modifier: Modifier = Modifier
) {
    var darkMode by remember { mutableStateOf(settingsManager.isDarkMode) }
    var notificationsEnabled by remember { mutableStateOf(settingsManager.isNotificationsEnabled) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        SettingItem(
            title = "Dark Mode",
            description = "Enable dark theme",
            checked = darkMode,
            onCheckedChange = { 
                darkMode = it
                settingsManager.isDarkMode = it
            }
        )

        SettingItem(
            title = "Notifications",
            description = "Enable push notifications",
            checked = notificationsEnabled,
            onCheckedChange = { 
                notificationsEnabled = it
                settingsManager.isNotificationsEnabled = it
            }
        )
    }
}

@Composable
private fun SettingItem(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
} 