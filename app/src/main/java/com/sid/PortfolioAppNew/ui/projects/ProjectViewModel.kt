package com.sid.PortfolioAppNew.ui.projects

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid.PortfolioAppNew.data.models.Project
import com.sid.PortfolioAppNew.data.models.ProjectCategory
import com.sid.PortfolioAppNew.data.models.ProjectStatus
import com.sid.PortfolioAppNew.data.remote.FirestoreService
import com.sid.PortfolioAppNew.data.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val repository: ProjectRepository,
    private val firestoreService: FirestoreService
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProjectUiState>(ProjectUiState.Loading)
    val uiState: StateFlow<ProjectUiState> = _uiState

    private val _selectedProject = MutableStateFlow<Project?>(null)
    val selectedProject: StateFlow<Project?> = _selectedProject

    init {
        loadProjects()
    }

    fun loadProjects() {
        Log.d("ProjectViewModel", "Starting to load projects")
        _uiState.value = ProjectUiState.Loading

        viewModelScope.launch {
            try {
                firestoreService.getProjects()
                    .onEach { projects ->
                        Log.d("ProjectViewModel", "Received ${projects.size} projects from Firestore")
                        if (projects.isEmpty()) {
                            Log.w("ProjectViewModel", "No projects found in Firestore, using sample data")
                            _uiState.value = ProjectUiState.Success(getSampleProjects())
                        } else {
                            _uiState.value = ProjectUiState.Success(projects)
                        }
                    }
                    .catch { error ->
                        Log.e("ProjectViewModel", "Error loading projects: ${error.message}", error)
                        _uiState.value = ProjectUiState.Success(getSampleProjects())
                    }
                    .launchIn(this)
            } catch (e: Exception) {
                Log.e("ProjectViewModel", "Exception in loadProjects: ${e.message}", e)
                _uiState.value = ProjectUiState.Success(getSampleProjects())
            }
        }
    }

    fun selectProject(project: Project) {
        _selectedProject.value = project
    }

    fun clearSelectedProject() {
        _selectedProject.value = null
    }

    private fun getSampleProjects(): List<Project> {
        return listOf(
            Project(
                id = "1",
                title = "Portfolio App",
                shortDesc = "A modern portfolio app built with Jetpack Compose",
                longDesc = "A showcase of my work and skills using modern Android development tools",
                images = listOf("https://example.com/portfolio.jpg"),
                githubLink = "https://github.com/username/portfolio",
                demoLink = "https://play.google.com/store/apps/details?id=com.example.portfolio",
                technologies = listOf("Kotlin", "Jetpack Compose", "Firebase"),
                category = ProjectCategory.ANDROID,
                status = ProjectStatus.COMPLETED
            ),
            Project(
                id = "2",
                title = "E-commerce App",
                shortDesc = "A full-featured e-commerce application",
                longDesc = "Complete shopping experience with cart, checkout, and payment integration",
                images = listOf("https://example.com/ecommerce.jpg"),
                githubLink = "https://github.com/username/ecommerce",
                demoLink = "https://play.google.com/store/apps/details?id=com.example.ecommerce",
                technologies = listOf("Kotlin", "MVVM", "Room", "Retrofit"),
                category = ProjectCategory.ANDROID,
                status = ProjectStatus.IN_PROGRESS
            )
        )
    }
}

sealed class ProjectUiState {
    object Loading : ProjectUiState()
    data class Success(val projects: List<Project>) : ProjectUiState()
    data class Error(val message: String) : ProjectUiState()
} 