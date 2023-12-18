package com.example.palto.data.repository

import com.example.palto.data.Result
import com.example.palto.data.network.ServerDataSource
import com.example.palto.data.model.LoggedInUser
import com.example.palto.data.model.Tokens

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class TokensRepository(val dataSource: ServerDataSource) {

    var tokens: Tokens? = null
        private set

    /*
    val isLoggedIn: Boolean
        get() = user != null
     */

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        tokens = null
    }

    /*
    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }
    */

    private fun setTokens(tokens: Tokens) {
        this.tokens = tokens
    }
}