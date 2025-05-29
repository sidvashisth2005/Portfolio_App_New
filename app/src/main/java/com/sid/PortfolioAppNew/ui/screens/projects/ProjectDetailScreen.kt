package com.sid.PortfolioAppNew.ui.screens.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sid.PortfolioAppNew.ui.components.NeonTopAppBar
import com.sid.PortfolioAppNew.ui.components.NeonButton
import com.sid.PortfolioAppNew.utils.*
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class, ExperimentalLayoutApi::class)
@Composable
fun ProjectDetailScreen(
    projectId: String,
    onNavigateBack: () -> Unit,
    viewModel: ProjectsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val urlUtils = rememberUrlUtils()
    val context = LocalContext.current
    val cacheManager = remember { CacheManager(context) }
    val networkUtils = remember { NetworkUtils(context) }
    val githubUtils = remember { GitHubUtils(cacheManager, networkUtils) }
    val downloadQueueManager = remember { DownloadQueueManager() }
    val downloadQueue by downloadQueueManager.downloadQueue.collectAsState()
    val isNetworkAvailable by networkUtils.observeNetworkState().collectAsState(initial = true)
    val coroutineScope = rememberCoroutineScope()
    var downloadState by remember { mutableStateOf<DownloadState?>(null) }

    LaunchedEffect(projectId) {
        viewModel.getProject(projectId)
    }

    Scaffold(
        topBar = {
            NeonTopAppBar(
                title = "Project Details",
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is ProjectsUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    CircularProgressIndicator()
                }
            }
            is ProjectsUiState.Success -> {
                val project = (uiState as ProjectsUiState.Success).projects.firstOrNull()
                if (project != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Network Status
                        if (!isNetworkAvailable) {
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                color = MaterialTheme.colorScheme.errorContainer
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.WifiOff,
                                        contentDescription = "No internet",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "No internet connection",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }

                        // Image Gallery
                        if (project.images.isNotEmpty()) {
                            val pagerState = rememberPagerState()
                            HorizontalPager(
                                count = project.images.size,
                                state = pagerState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            ) { page ->
                                AsyncImage(
                                    model = project.images[page],
                                    contentDescription = "Project mockup ${page + 1}",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }

                        // Project Details
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = project.title,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Technology Tags
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                project.technologies.forEach { tech ->
                                    Surface(
                                        modifier = Modifier.clip(MaterialTheme.shapes.small),
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                    ) {
                                        Text(
                                            text = tech,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = project.longDesc,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // Action Buttons
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                NeonButton(
                                    text = "View on GitHub",
                                    onClick = { urlUtils.openUrl(context, project.githubLink) },
                                    modifier = Modifier.weight(1f)
                                )

                                NeonButton(
                                    text = when (downloadState) {
                                        is DownloadState.Progress -> "Downloading..."
                                        is DownloadState.Starting -> "Starting..."
                                        is DownloadState.Success -> "Downloaded"
                                        is DownloadState.Error -> "Retry Download"
                                        null -> "Download Demo"
                                    },
                                    onClick = {
                                        if (downloadState !is DownloadState.Progress) {
                                            val apkUrl = githubUtils.getApkUrl(project.title)
                                            downloadQueueManager.addToQueue(apkUrl, project.title)
                                            coroutineScope.launch {
                                                githubUtils.downloadApk(context, apkUrl)
                                                    .collect { state ->
                                                        downloadState = state
                                                        if (state is DownloadState.Success || state is DownloadState.Error) {
                                                            downloadQueueManager.removeFromQueue(apkUrl)
                                                        }
                                                    }
                                            }
                                        }
                                    },
                                    modifier = Modifier.weight(1f),
                                    enabled = isNetworkAvailable
                                )
                            }

                            // Download Progress
                            if (downloadState is DownloadState.Progress) {
                                val progress = (downloadState as DownloadState.Progress)
                                Spacer(modifier = Modifier.height(16.dp))
                                LinearProgressIndicator(
                                    progress = progress.percentage / 100f,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Downloading: ${progress.percentage}%",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                    )
                                    Text(
                                        text = formatSpeed(progress.speedBytesPerSecond),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                    )
                                }
                            }

                            // Error Message
                            if (downloadState is DownloadState.Error) {
                                val error = (downloadState as DownloadState.Error).message
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = error,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }

                            // Download Queue
                            if (downloadQueue.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Download Queue",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                downloadQueue.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = item.projectName,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                        )
                                        IconButton(
                                            onClick = { downloadQueueManager.removeFromQueue(item.url) }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Remove from queue",
                                                tint = MaterialTheme.colorScheme.error
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Project not found",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            is ProjectsUiState.Error -> {
                val message = (uiState as ProjectsUiState.Error).message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

private fun formatSpeed(bytesPerSecond: Long): String {
    return when {
        bytesPerSecond >= 1024 * 1024 -> String.format("%.1f MB/s", bytesPerSecond / (1024.0 * 1024.0))
        bytesPerSecond >= 1024 -> String.format("%.1f KB/s", bytesPerSecond / 1024.0)
        else -> "$bytesPerSecond B/s"
    }
} 