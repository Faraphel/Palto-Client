package com.example.palto.domain
import java.io.Serializable
import java.time.LocalTime

/**
 * Data class that captures tokens for logged in users retrieved from LoginRepository
 */
data class Attendance(
    val id: Int,
    val student: User,
    val date: LocalTime
) : Serializable