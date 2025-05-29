package com.sid.PortfolioAppNew.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid.PortfolioAppNew.data.model.PortfolioState
import com.sid.PortfolioAppNew.data.repository.PortfolioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val repository: PortfolioRepository
) : ViewModel() {
    private val _portfolioState = MutableStateFlow<PortfolioState>(PortfolioState.Loading)
    val portfolioState: StateFlow<PortfolioState> = _portfolioState.asStateFlow()
    
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    init {
        loadPortfolio()
    }

    fun loadPortfolio() {
        viewModelScope.launch {
            try {
                _portfolioState.value = PortfolioState.Loading
                val data = repository.getPortfolio()
                _portfolioState.value = PortfolioState.Success(data)
            } catch (e: Exception) {
                _portfolioState.value = PortfolioState.Error(e.message ?: "Failed to load portfolio")
            }
        }
    }

    fun refreshPortfolio() {
        viewModelScope.launch {
            try {
                _isRefreshing.value = true
                val data = repository.getPortfolio()
                _portfolioState.value = PortfolioState.Success(data)
            } catch (e: Exception) {
                _portfolioState.value = PortfolioState.Error(e.message ?: "Failed to refresh portfolio")
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }
} 