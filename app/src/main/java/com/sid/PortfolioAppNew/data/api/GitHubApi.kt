package com.sid.PortfolioAppNew.data.api

import retrofit2.http.GET
import retrofit2.http.Path

data class GitHubRepo(
    val id: Int,
    val name: String,
    val description: String?,
    val stargazers_count: Int,
    val language: String?,
    val topics: List<String>,
    val html_url: String
)

interface GitHubApi {
    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): List<GitHubRepo>
}

object GitHubApiConfig {
    const val BASE_URL = "https://api.github.com/"
    const val USERNAME = "yourusername" // Replace with your GitHub username
} 