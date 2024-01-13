package com.example.palto.domain

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    val id: Int,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String
)
