package com.example.palto.data.model
import java.io.Serializable

/**
 * Data class that captures tokens for logged in users retrieved from LoginRepository
 */
data class StudentCard(
    val id: String
) : Serializable