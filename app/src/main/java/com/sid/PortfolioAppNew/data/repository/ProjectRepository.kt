package com.sid.PortfolioAppNew.data.repository

import com.sid.PortfolioAppNew.data.models.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    fun getProjects(): Flow<List<Project>>
    suspend fun getProject(id: String): Project?
} 