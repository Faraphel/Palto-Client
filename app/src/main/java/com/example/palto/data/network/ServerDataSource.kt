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

    fun requestToken(
        hostname: String,
        username: String,
        password: String
    ): Result<Tokens> {
        try {
            val tokens = Tokens(
                refresh = "aa",
                access = "bb"
            )
            return Result.Success(tokens)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun refreshToken(current_tokens: Tokens): Result<Tokens> {
        return Result.Success(current_tokens)
    }

    fun verifyToken(): Boolean {
        return true
    }

    fun login(
        hostname: String,
        username: String,
        password: String
    ): Result<LoggedInUser> {
        try {
            val fakeUser = LoggedInUser(
                java.util.UUID.randomUUID().toString(),
                "dede",
                "Lucie",
                "Doe",
                "aa@free.fr",
                )
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() { }
}