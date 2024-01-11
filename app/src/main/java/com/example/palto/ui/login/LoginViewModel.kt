package com.example.palto.ui.login

import androidx.lifecycle.ViewModel

/**
 * View Model to check dynamically the values of the form.
 */
class LoginViewModel() : ViewModel() {
    /*
    private val _loginFormState = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginFormState

    fun loginDataChanged(
        hostname: String,
        username: String,
        password: String) {
        if (!isHostNameValid(hostname)) {
            _loginForm.value = LoginFormState(hostnameError = R.string.invalid_hostname)
        } else if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }
    private fun isHostNameValid(hostname: String): Boolean {
        return hostname.isNotBlank()
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
     */
}


