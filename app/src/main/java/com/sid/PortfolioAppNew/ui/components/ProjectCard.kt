package com.sid.PortfolioAppNew.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.sid.PortfolioAppNew.R
import com.sid.PortfolioAppNew.data.models.Project
import com.sid.PortfolioAppNew.data.models.ProjectStatus
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Error
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

private const val TAG = "ProjectCard"

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectCard(
    project: Project,
    onClick: () -> Unit
) {
    Log.d(TAG, "Rendering ProjectCard for project: ${project.title}")
    Log.d(TAG, "Preview image URL: ${project.previewImageUrl}")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            // Project Image with loading and error states
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {
                val imageUrl = project.previewImageUrl.ifEmpty { project.images.firstOrNull() }
                Log.d(TAG, "Using image URL: $imageUrl")

                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "${project.title} Preview",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        placeholder = painterResource(id = R.drawable.image_placeholder),
                        error = painterResource(id = R.drawable.image_error)
                    )
                } else {
                    // No image available placeholder
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = "No image available",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No preview available",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Project Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                    
                    // Status Chip
                    Surface(
                        modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                        color = when (project.status) {
                            ProjectStatus.COMPLETED -> MaterialTheme.colorScheme.primary
                            ProjectStatus.IN_PROGRESS -> MaterialTheme.colorScheme.secondary
                            ProjectStatus.ON_HOLD -> MaterialTheme.colorScheme.error
                            ProjectStatus.PLANNED -> MaterialTheme.colorScheme.tertiary
                        }
                    ) {
                        Text(
                            text = project.status.name.replace("_", " "),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = project.shortDesc,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Technologies
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    project.technologies.take(3).forEach { tech ->
                        AssistChip(
                            onClick = { },
                            label = { Text(tech) }
                        )
                    }
                    if (project.technologies.size > 3) {
                        AssistChip(
                            onClick = { },
                            label = { Text("+${project.technologies.size - 3} more") }
                        )
                    }
                }
            }
        }
    }
} 