package com.example.palto.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.palto.R
import com.example.palto.data.repository.TokenRepository
import com.example.palto.data.repository.UserRepository
import com.example.palto.domain.User
import kotlinx.coroutines.launch

/**
 * LoginViewModel to access information about the logged in user and login form.
 */
class LoginViewModel(
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository
): ViewModel() {

    private var _result = MutableLiveData<LoginResult>()
    val result = _result as LiveData<LoginResult>

    // User is initially set to null to be disconnected.
    private var _user = MutableLiveData<User?>(null)
    val user = _user as LiveData<User?>

    /*
    private val _loginFormState = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginFormState
    */

    fun login(
        hostname: String,
        username: String,
        password: String) {

        // Coroutine runs in background.
        viewModelScope.launch {
            try {
                tokenRepository.authenticate(hostname, username, password)
                _user.value = User(-1, username, "", "", "")
                _result.value = LoginResult(success = true)
            } catch (e: Exception) {
                Log.e("Palto", "Connection error: " + e.message)
                _result.value = LoginResult(
                    success = false,
                    error = R.string.login_failed,
                    exception = e
                )
            }
        }
    }

    fun loginAnonymous() {
        _user.value = User(-2, "anonymous", "", "", "")
        _result.value = LoginResult(success = true)
    }

    fun logout() {
        _user.value = null
        _result.value = LoginResult(success = false)
    }

    /*
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

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(
                    tokenRepository = TokenRepository(),
                    userRepository = UserRepository()
                ) as T
            }
        }
    }
}