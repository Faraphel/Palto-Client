package com.example.palto.domain

data class Card(
    val id: String,
    val uid: ByteArray,
    val department: String,
    val owner: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        return uid.contentEquals(other.uid)
    }

    override fun hashCode(): Int {
        return uid.contentHashCode()
    }

}