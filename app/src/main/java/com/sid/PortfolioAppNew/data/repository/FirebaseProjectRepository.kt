package com.sid.PortfolioAppNew.data.repository

import android.util.Log
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
    private val TAG = "FirebaseProjectRepository"

    override fun getProjects(): Flow<List<Project>> = flow {
        try {
            Log.d(TAG, "Attempting to fetch projects from Firestore")
            firestoreService.getProjects().collect { projects ->
                if (projects.isEmpty()) {
                    Log.w(TAG, "No projects found in Firestore, falling back to sample data")
                    emit(SampleProjects.projects)
                } else {
                    Log.d(TAG, "Successfully fetched ${projects.size} projects from Firestore")
                    emit(projects)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching projects from Firestore: ${e.message}", e)
            Log.d(TAG, "Falling back to sample data")
            emit(SampleProjects.projects)
        }
    }.catch { error ->
        Log.e(TAG, "Flow error in getProjects: ${error.message}", error)
        emit(SampleProjects.projects)
    }
    
    override suspend fun getProject(id: String): Project? {
        return try {
            Log.d(TAG, "Attempting to fetch project with ID: $id from Firestore")
            firestoreService.getProject(id) ?: run {
                Log.w(TAG, "Project not found in Firestore, checking sample data")
                SampleProjects.projects.find { it.id == id }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching project from Firestore: ${e.message}", e)
            Log.d(TAG, "Falling back to sample data")
            SampleProjects.projects.find { it.id == id }
        }
    }
} 