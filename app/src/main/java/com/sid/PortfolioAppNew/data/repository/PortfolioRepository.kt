package com.sid.PortfolioAppNew.data.repository

interface PortfolioRepository {
    suspend fun getPortfolio(): Map<String, Any>
} 