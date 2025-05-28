package com.sid.PortfolioAppNew.ui.screens.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.sid.PortfolioAppNew.data.model.PortfolioState
import com.sid.PortfolioAppNew.ui.viewmodel.PortfolioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    onNavigateBack: () -> Unit
) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Contact",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            ContactInfoSection(uriHandler)
            Spacer(modifier = Modifier.height(32.dp))
            SocialLinksSection(uriHandler)
        }
    }
}

@Composable
private fun ContactInfoSection(
    uriHandler: androidx.compose.ui.platform.UriHandler
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Contact Information",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactItem(
            icon = Icons.Default.Email,
            text = "siddhantvashisth05@gmail.com",
            onClick = { uriHandler.openUri("mailto:siddhantvashisth05@gmail.com") }
        )

        ContactItem(
            icon = Icons.Default.Phone,
            text = "+91 8871592579",
            onClick = { uriHandler.openUri("tel:+918871592579") }
        )

        ContactItem(
            icon = Icons.Default.LocationOn,
            text = "Guna, Madhya Pradesh, India",
            onClick = { uriHandler.openUri("geo:0,0?q=Guna,Madhya Pradesh,India") }
        )
    }
}

@Composable
private fun SocialLinksSection(
    uriHandler: androidx.compose.ui.platform.UriHandler
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Social Links",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SocialButton(
                icon = Icons.Default.Person,
                onClick = { uriHandler.openUri("https://linkedin.com/in/siddhant-vashisth") }
            )

            SocialButton(
                icon = Icons.Default.Code,
                onClick = { uriHandler.openUri("https://github.com/sidvashisth2005") }
            )
        }
    }
}

@Composable
private fun ContactItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun SocialButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
} 