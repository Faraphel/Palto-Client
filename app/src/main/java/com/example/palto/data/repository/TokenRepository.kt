package com.example.palto.data.repository

import com.example.palto.data.network.PaltoApi
import com.example.palto.data.network.model.UserCredentials
import com.example.palto.domain.Tokens

/**
 * Class that requests authentication tokens from Palto server.
 */
class TokenRepository() {

    private var tokens: Tokens? = null

    suspend fun authenticate(
        hostname: String,
        username: String,
        password: String
    ) {
        PaltoApi.createService("http://$hostname:8000/")
        val tokens = PaltoApi.retrofitService.getTokens((UserCredentials(username, password)))
        this.tokens = tokens
    }
}