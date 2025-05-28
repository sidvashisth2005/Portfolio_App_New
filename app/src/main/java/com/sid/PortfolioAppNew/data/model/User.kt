package com.sid.PortfolioAppNew.data.model

data class User(
    val id: String = "",
    val email: String = "",
    val displayName: String = "",
    val photoUrl: String? = null,
    val bio: String = "",
    val skills: List<String> = emptyList(),
    val projects: List<String> = emptyList()
) 