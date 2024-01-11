package com.example.palto.ui.login

/**
 * Authentication result : success is true if connected or error message with exception.
 */
data class LoginResult(
    val success: Boolean,
    val error: Int? = null, // Id of the string resource to display to the user.
    val exception: Exception? = null
)