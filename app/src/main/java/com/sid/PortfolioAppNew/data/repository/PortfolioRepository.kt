package com.sid.PortfolioAppNew.data.repository

interface PortfolioRepository {
    // Add your repository methods here
    // For example:
    // suspend fun getProjects(): List<Project>
    // suspend fun getSkills(): List<Skill>
    // etc.
    suspend fun getPortfolio(): Map<String, Any>
} 