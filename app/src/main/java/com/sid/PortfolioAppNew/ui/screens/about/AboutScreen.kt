package com.sid.PortfolioAppNew.ui.screens.about

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.sid.PortfolioAppNew.ui.components.*
import com.sid.PortfolioAppNew.ui.theme.*
import com.sid.PortfolioAppNew.utils.UrlUtils
import com.sid.PortfolioAppNew.ui.components.PdfViewer
import android.net.Uri
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.sid.PortfolioAppNew.utils.rememberUrlUtils
import com.sid.PortfolioAppNew.ui.components.NeonTopAppBar
import com.sid.PortfolioAppNew.ui.components.NeonCard
import com.sid.PortfolioAppNew.ui.components.NeonButton
import com.sid.PortfolioAppNew.ui.components.NeonChip
import android.content.Intent
import android.widget.Toast

data class ContactInfo(
    val type: String,
    val value: String,
    val icon: @Composable () -> Unit,
    val action: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val urlUtils = rememberUrlUtils()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            NeonTopAppBar(
                title = "About Me",
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Android Developer & AR Enthusiast",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            // Bio Section
            NeonCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Bio",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = "I'm a passionate Android developer with expertise in modern Android development technologies. " +
                               "My focus is on creating beautiful, performant, and user-friendly applications. " +
                               "I love exploring new technologies, especially in the AR/VR space, and I'm always " +
                               "looking for ways to push the boundaries of what's possible on mobile devices.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
            
            // Contact Section
            NeonCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Contact",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    val contactInfo = listOf(
                        ContactInfo(
                            type = "Email",
                            value = "siddhantvashisth05@gmail.com",
                            icon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                            action = {
                                try {
                                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                                        data = Uri.parse("mailto:siddhantvashisth05@gmail.com")
                                    }
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Could not open email client", Toast.LENGTH_SHORT).show()
                                }
                            }
                        ),
                        ContactInfo(
                            type = "Phone",
                            value = "+91 8871592579",
                            icon = { Icon(Icons.Default.Phone, contentDescription = "Phone") },
                            action = {
                                try {
                                    val intent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:+918871592579")
                                    }
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Could not open phone dialer", Toast.LENGTH_SHORT).show()
                                }
                            }
                        ),
                        ContactInfo(
                            type = "LinkedIn",
                            value = "linkedin.com/in/siddhant-vashisth-04887b29b",
                            icon = { Icon(Icons.Default.Person, contentDescription = "LinkedIn") },
                            action = {
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse("https://www.linkedin.com/in/siddhant-vashisth-04887b29b/")
                                    }
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Could not open LinkedIn", Toast.LENGTH_SHORT).show()
                                }
                            }
                        ),
                        ContactInfo(
                            type = "GitHub",
                            value = "github.com/sidvashisth2005",
                            icon = { Icon(Icons.Default.Code, contentDescription = "GitHub") },
                            action = {
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse("https://github.com/sidvashisth2005")
                                    }
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Could not open GitHub", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    )
                    
                    contactInfo.forEach { contact ->
                        ContactItem(contact = contact)
                    }
                }
            }
            
            // Download Resume Button
            NeonButton(
                text = "Download Resume",
                onClick = { urlUtils.downloadResume(context) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )
        }
    }
}

@Composable
fun ContactItem(contact: ContactInfo) {
    val context = LocalContext.current
    var isPressed by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = contact.action
            )
            .background(
                color = if (isPressed) 
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else 
                    Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = contact.action,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            contact.icon()
        }
        
        Column {
            Text(
                text = contact.type,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            
            Text(
                text = contact.value,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isPressed)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun SocialButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(56.dp)
            .clickable(onClick = onClick),
        shape = CircleShape,
        color = Color(0xFF1A1A1A).copy(alpha = 0.9f)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = NeonBlue,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun AboutSection(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun EducationSection(education: List<Map<String, Any>>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Education",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        education.forEach { edu ->
            EducationItem(education = edu)
        }
    }
}

@Composable
private fun EducationItem(education: Map<String, Any>) {
    val degree = education["degree"] as? String ?: ""
    val institution = education["institution"] as? String ?: ""
    val year = education["year"] as? String ?: ""

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = degree,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = institution,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Text(
                text = year,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
private fun ExperienceSection(experience: List<Map<String, Any>>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Experience",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        experience.forEach { exp ->
            ExperienceItem(experience = exp)
        }
    }
}

@Composable
private fun ExperienceItem(experience: Map<String, Any>) {
    val title = experience["title"] as? String ?: ""
    val company = experience["company"] as? String ?: ""
    val period = experience["period"] as? String ?: ""
    val description = experience["description"] as? String ?: ""

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = company,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Text(
                text = period,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
} 