package com.sid.PortfolioAppNew.data.repository

import com.sid.PortfolioAppNew.data.models.Project
import com.sid.PortfolioAppNew.data.remote.FirestoreService
import com.sid.PortfolioAppNew.data.sample.SampleProjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseProjectRepository @Inject constructor(
    private val firestoreService: FirestoreService
) : ProjectRepository {
    override fun getProjects(): Flow<List<Project>> = flow {
        try {
            firestoreService.getProjects().collect { projects ->
                emit(projects)
            }
        } catch (e: Exception) {
            // Fallback to sample data if Firestore fails
            emit(SampleProjects.projects)
        }
    }
    
    override suspend fun getProject(id: String): Project? {
        return try {
            firestoreService.getProject(id) ?: SampleProjects.projects.find { it.id == id }
        } catch (e: Exception) {
            SampleProjects.projects.find { it.id == id }
        }
    }
} 