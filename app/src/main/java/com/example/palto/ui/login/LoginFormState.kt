package com.example.palto.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val hostnameError: Int? = null,
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)