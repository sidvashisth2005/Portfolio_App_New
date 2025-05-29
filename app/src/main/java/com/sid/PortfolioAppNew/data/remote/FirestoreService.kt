package com.sid.PortfolioAppNew.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sid.PortfolioAppNew.data.models.Project
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreService @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val projectsCollection = firestore.collection("projects")
    private val TAG = "FirestoreService"

    fun getProjects(): Flow<List<Project>> = callbackFlow {
        Log.d(TAG, "Starting to fetch projects from Firestore")
        
        val subscription = projectsCollection
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e(TAG, "Error fetching projects: ${error.message}", error)
                    close(error)
                    return@addSnapshotListener
                }

                try {
                    val projects = snapshot?.documents?.mapNotNull { doc ->
                        try {
                            Log.d(TAG, "Processing document: ${doc.id}")
                            val data = doc.data
                            Log.d(TAG, "Document data: $data")
                            
                            val project = doc.toObject(Project::class.java)?.copy(id = doc.id)
                            if (project != null) {
                                Log.d(TAG, "Project loaded: ${project.title}")
                                Log.d(TAG, "Preview image URL: ${project.previewImageUrl}")
                                Log.d(TAG, "Images list: ${project.images}")
                            }
                            project
                        } catch (e: Exception) {
                            Log.e(TAG, "Error converting document ${doc.id}: ${e.message}", e)
                            null
                        }
                    } ?: emptyList()
                    
                    if (projects.isEmpty()) {
                        Log.w(TAG, "No projects found in Firestore")
                    } else {
                        Log.d(TAG, "Successfully fetched ${projects.size} projects")
                        projects.forEach { project ->
                            Log.d(TAG, "Project: ${project.title}, Preview URL: ${project.previewImageUrl}")
                        }
                    }
                    trySend(projects)
                } catch (e: Exception) {
                    Log.e(TAG, "Error processing snapshot: ${e.message}", e)
                    close(e)
                }
            }
        
        awaitClose { 
            Log.d(TAG, "Removing snapshot listener")
            subscription.remove() 
        }
    }

    suspend fun getProject(id: String): Project? {
        return try {
            Log.d(TAG, "Fetching project with ID: $id")
            val doc = projectsCollection.document(id).get().await()
            val project = doc.toObject(Project::class.java)?.copy(id = doc.id)
            if (project != null) {
                Log.d(TAG, "Successfully fetched project: ${project.title}")
                Log.d(TAG, "Preview image URL: ${project.previewImageUrl}")
                Log.d(TAG, "Images list: ${project.images}")
            } else {
                Log.w(TAG, "Project not found with ID: $id")
            }
            project
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching project $id: ${e.message}", e)
            null
        }
    }

    // Test function to verify Firestore connection
    fun testConnection() {
        projectsCollection
            .get()
            .addOnSuccessListener { documents ->
                Log.d(TAG, "Successfully fetched ${documents.size()} documents")
                documents.forEach { doc ->
                    Log.d(TAG, "Document ID: ${doc.id}")
                    Log.d(TAG, "Document data: ${doc.data}")
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error fetching documents", e)
            }
    }
} 