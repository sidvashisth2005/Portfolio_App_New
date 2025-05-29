package com.sid.PortfolioAppNew.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.sid.PortfolioAppNew.data.repository.FirebaseRepository
import com.sid.PortfolioAppNew.data.repository.PortfolioRepository
import com.sid.PortfolioAppNew.data.repository.ProjectRepository
import com.sid.PortfolioAppNew.data.repository.FirebaseProjectRepository
import com.sid.PortfolioAppNew.data.remote.FirestoreService
import com.sid.PortfolioAppNew.utils.SettingsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideSettingsManager(
        @ApplicationContext context: Context
    ): SettingsManager {
        return SettingsManager(context)
    }

    @Provides
    @Singleton
    fun providePortfolioRepository(): PortfolioRepository {
        return FirebaseRepository()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirestoreService(firestore: FirebaseFirestore): FirestoreService {
        return FirestoreService(firestore)
    }

    @Provides
    @Singleton
    fun provideProjectRepository(firestoreService: FirestoreService): ProjectRepository {
        return FirebaseProjectRepository(firestoreService)
    }
} 