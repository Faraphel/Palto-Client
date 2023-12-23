package com.example.palto.model
import java.io.Serializable

/**
 * Data class that captures tokens for logged in users retrieved from LoginRepository
 */
data class Session(
    val id: String
) : Serializable