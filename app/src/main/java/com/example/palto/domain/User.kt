package com.example.palto.domain

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    //Not in the domain layer
    //val id: String,
    val username: String,
    val first_name: String,
    val last_name: String,
    val email: String
)
