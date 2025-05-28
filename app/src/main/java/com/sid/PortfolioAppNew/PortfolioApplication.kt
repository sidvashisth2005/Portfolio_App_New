package com.sid.PortfolioAppNew

import android.app.Application
import com.google.firebase.FirebaseApp
import com.sid.PortfolioAppNew.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PortfolioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        
        // Initialize Koin
        startKoin {
            androidContext(this@PortfolioApplication)
            modules(appModule)
        }
    }
} 