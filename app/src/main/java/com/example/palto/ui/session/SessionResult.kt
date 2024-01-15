package com.example.palto.ui.session

/**
 * Authentication result : success is true if connected or error message with exception.
 */
data class SessionResult(
    val success: Boolean,
    val message: Int, // Id of the string resource to display to the user
    val username: String
)