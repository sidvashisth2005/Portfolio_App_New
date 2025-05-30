package com.sid.PortfolioAppNew.ui.screens.arx

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

sealed class ARXUiState {
    object Loading : ARXUiState()
    object Ready : ARXUiState()
    data class Error(val message: String) : ARXUiState()
}

@HiltViewModel
class ARXViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<ARXUiState>(ARXUiState.Loading)
    val uiState: StateFlow<ARXUiState> = _uiState

    init {
        initializeARSession()
    }

    private fun initializeARSession() {
        // TODO: Implement AR session initialization
        // For now, we'll simulate a loading state
        _uiState.value = ARXUiState.Loading
    }

    fun retry() {
        _uiState.value = ARXUiState.Loading
        initializeARSession()
    }
} 