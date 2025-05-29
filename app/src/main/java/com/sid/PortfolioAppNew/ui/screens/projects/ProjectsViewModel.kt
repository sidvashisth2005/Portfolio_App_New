package com.sid.PortfolioAppNew.ui.screens.projects

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid.PortfolioAppNew.data.models.Project
import com.sid.PortfolioAppNew.data.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val repository: ProjectRepository
) : ViewModel() {
    private val TAG = "ProjectsViewModel"
    private val _uiState = MutableStateFlow<ProjectsUiState>(ProjectsUiState.Loading)
    val uiState: StateFlow<ProjectsUiState> = _uiState.asStateFlow()

    init {
        Log.d(TAG, "Initializing ProjectsViewModel")
        viewModelScope.launch {
            repository.getProjects()
                .catch { error ->
                    Log.e(TAG, "Error in projects flow: ${error.message}", error)
                    _uiState.value = ProjectsUiState.Error(error.message ?: "Unknown error")
                }
                .collect { projects ->
                    Log.d(TAG, "Received ${projects.size} projects")
                    _uiState.value = ProjectsUiState.Success(projects)
                }
        }
    }

    fun getProject(id: String) {
        Log.d(TAG, "Fetching project with ID: $id")
        viewModelScope.launch {
            _uiState.value = ProjectsUiState.Loading
            try {
                val project = repository.getProject(id)
                if (project != null) {
                    Log.d(TAG, "Successfully fetched project: ${project.title}")
                    _uiState.value = ProjectsUiState.Success(listOf(project))
                } else {
                    Log.w(TAG, "Project not found with ID: $id")
                    _uiState.value = ProjectsUiState.Error("Project not found")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching project: ${e.message}", e)
                _uiState.value = ProjectsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class ProjectsUiState {
    object Loading : ProjectsUiState()
    data class Success(val projects: List<Project>) : ProjectsUiState()
    data class Error(val message: String) : ProjectsUiState()
} 