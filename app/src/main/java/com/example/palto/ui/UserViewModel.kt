package com.example.palto.ui

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
import com.example.palto.ui.login.LoginResult
import kotlinx.coroutines.launch

class UserViewModel(
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository
): ViewModel() {

    private var _result = MutableLiveData<LoginResult>()
    val result = _result as LiveData<LoginResult>

    // User is initially set to null to be disconnected.
    private var _user = MutableLiveData<User?>(null)
    val user = _user as LiveData<User?>

    fun login(
        hostname: String,
        username: String,
        password: String) {

        // Coroutine runs in background.
        viewModelScope.launch {
            try {
                tokenRepository.authenticate(hostname, username, password)
                _user.value = User(
                    username,
                    "",
                    "",
                    ""
                )
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
        _user.value = User(
            "anonymous", "", "", ""
        )
        _result.value = LoginResult(success = true)
    }

    fun logout() {
        _user.value = null
        _result.value = LoginResult(success = false)
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(
                    tokenRepository = TokenRepository(),
                    userRepository = UserRepository()
                ) as T
            }
        }
    }
}