package com.example.palto.data.repository

import com.example.palto.data.Result
import com.example.palto.data.network.ServerDataSource
import com.example.palto.data.model.LoggedInUser
import com.example.palto.data.model.Tokens

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: ServerDataSource) {

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(
        hostname: String,
        username: String,
        password: String
    ): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(hostname, username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}