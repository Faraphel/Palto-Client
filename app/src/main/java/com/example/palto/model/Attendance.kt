package com.example.palto.model
import java.io.Serializable

/**
 * Data class that captures tokens for logged in users retrieved from LoginRepository
 */
data class Attendance(
    val date: String,
    val access: String
) : Serializable