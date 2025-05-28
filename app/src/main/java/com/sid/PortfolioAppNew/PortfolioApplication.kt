package com.sid.PortfolioAppNew

import android.app.Application
import com.sid.PortfolioAppNew.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PortfolioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@PortfolioApplication)
            modules(appModule)
        }
    }
} 