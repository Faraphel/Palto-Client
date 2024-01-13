package com.example.palto.domain
import java.io.Serializable

/**
 * Data class that captures tokens for logged in users retrieved from LoginRepository
 */
data class Session(
    val id: Int,
    val name: String,
    var attendances: List<Attendance>
    // When the list is updated, it is replaced by a new one.
)