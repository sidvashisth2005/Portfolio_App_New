package com.sid.PortfolioAppNew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.sid.PortfolioAppNew.data.model.User
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers

class FirebaseRepository : PortfolioRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    suspend fun signIn(email: String, password: String): Result<Unit> = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun signUp(email: String, password: String): Result<Unit> = try {
        auth.createUserWithEmailAndPassword(email, password).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun signOut() {
        auth.signOut()
    }

    fun getFirebaseUser(): FirebaseUser? = auth.currentUser

    fun getCurrentUser(): Flow<User?> = flow {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            val userDoc = firestore.collection("users").document(firebaseUser.uid).get().await()
            val user = userDoc.toObject(User::class.java)
            emit(user)
        } else {
            emit(null)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun updateUserProfile(user: User): Result<Unit> = try {
        val currentUser = auth.currentUser ?: throw Exception("No user logged in")
        firestore.collection("users").document(currentUser.uid).set(user).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun uploadFile(path: String, data: ByteArray): Result<String> = try {
        val ref = storage.reference.child(path)
        ref.putBytes(data).await()
        val downloadUrl = ref.downloadUrl.await()
        Result.success(downloadUrl.toString())
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun downloadFile(filePath: String): Result<ByteArray> = try {
        val ref = storage.reference.child(filePath)
        val maxSize = 1024L * 1024L // 1MB
        val bytes = ref.getBytes(maxSize).await()
        Result.success(bytes)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getSkills(): Result<List<String>> = try {
        val skillsDoc = firestore.collection("skills").document("list").get().await()
        val skills = skillsDoc.get("items") as? List<String> ?: emptyList()
        Result.success(skills)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getProjects(): Result<List<Map<String, Any>>> = try {
        val projectsSnapshot = firestore.collection("projects").get().await()
        val projects = projectsSnapshot.documents.mapNotNull { it.data }
        Result.success(projects)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPortfolio(): Map<String, Any> {
        return try {
            val document = firestore.collection("portfolio").document("data").get().await()
            document.data ?: emptyMap()
        } catch (e: Exception) {
            emptyMap()
        }
    }

    suspend fun updatePortfolioData(data: Map<String, Any>): Result<Unit> = try {
        val user = auth.currentUser ?: throw Exception("User not authenticated")
        firestore.collection("portfolios").document(user.uid).set(data).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
} 