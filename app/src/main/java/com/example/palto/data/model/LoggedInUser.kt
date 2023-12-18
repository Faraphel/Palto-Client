package com.example.palto.data.model

import java.io.Serializable

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val id: String,
    val username: String,
    val first_name: String,
    val last_name: String,
    val email: String
) : Serializable
