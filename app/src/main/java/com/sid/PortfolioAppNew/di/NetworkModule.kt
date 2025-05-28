package com.sid.PortfolioAppNew.di

import com.sid.PortfolioAppNew.data.api.GitHubApi
import com.sid.PortfolioAppNew.data.api.GitHubApiConfig
import com.sid.PortfolioAppNew.data.api.LinkedInApi
import com.sid.PortfolioAppNew.data.api.LinkedInApiConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApi(okHttpClient: OkHttpClient): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(GitHubApiConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLinkedInApi(okHttpClient: OkHttpClient): LinkedInApi {
        return Retrofit.Builder()
            .baseUrl(LinkedInApiConfig.BASE_URL)
            .client(okHttpClient.newBuilder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer ${LinkedInApiConfig.ACCESS_TOKEN}")
                        .build()
                    chain.proceed(request)
                }
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LinkedInApi::class.java)
    }
} 