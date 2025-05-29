package com.sid.PortfolioAppNew.ui.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sid.PortfolioAppNew.data.models.Project
import com.sid.PortfolioAppNew.ui.components.ProjectCard

@Composable
fun ProjectsScreen(
    onProjectClick: (Project) -> Unit,
    viewModel: ProjectViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (uiState) {
            is ProjectUiState.Loading -> {
                LoadingState()
            }
            is ProjectUiState.Success -> {
                val projects = (uiState as ProjectUiState.Success).projects
                if (projects.isEmpty()) {
                    EmptyState()
                } else {
                    ProjectsList(
                        projects = projects,
                        onProjectClick = onProjectClick
                    )
                }
            }
            is ProjectUiState.Error -> {
                ErrorState(
                    message = (uiState as ProjectUiState.Error).message,
                    onRetry = { viewModel.loadProjects() }
                )
            }
        }
    }
}

@Composable
private fun ProjectsList(
    projects: List<Project>,
    onProjectClick: (Project) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(projects) { project ->
            ProjectCard(
                project = project,
                onClick = { onProjectClick(project) }
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No projects found",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
} 