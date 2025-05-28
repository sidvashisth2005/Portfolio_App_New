package com.sid.PortfolioAppNew.data.model

sealed class PortfolioState {
    object Loading : PortfolioState()
    data class Success(val data: Map<String, Any>) : PortfolioState()
    data class Error(val message: String) : PortfolioState()
} 