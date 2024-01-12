package com.example.palto.domain
import java.io.Serializable

/**
 * Data class that captures tokens for logged in users retrieved from LoginRepository
 */
data class Session(
    val id: Int,
    val name: String,
    var attendances: List<User>
)