package com.sid.PortfolioAppNew.di

import com.sid.PortfolioAppNew.data.repository.PortfolioRepository
import com.sid.PortfolioAppNew.data.repository.FirebaseRepository
import com.sid.PortfolioAppNew.ui.viewmodel.PortfolioViewModel
import com.sid.PortfolioAppNew.ui.viewmodel.SettingsViewModel
import com.sid.PortfolioAppNew.utils.SettingsManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { SettingsManager(androidContext()) }
    single<PortfolioRepository> { FirebaseRepository() }
    viewModel { PortfolioViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
} 