package com.example.palto.data.network

import com.example.palto.data.Result
import com.example.palto.data.model.LoggedInUser
import com.example.palto.data.model.Tokens
import java.io.IOException

/**
 * Class that handles API calls.
 */
class ServerDataSource {

    private var hostname: String? = null

    fun requestToken(hostname: String, username: String, password: String): Result<Tokens> {
        try {
            val tokens = Tokens()
            return Result.Success()
        } catch () {
            return Result.Error()
        }
    }

    fun refreshToken(): Result<Tokens> {

    }

    fun verifyToken(): Boolean {

    }

    fun login(hostname: String, username: String, password: String): Result<LoggedInUser> {
        try {
            /*
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
            */
            return
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}