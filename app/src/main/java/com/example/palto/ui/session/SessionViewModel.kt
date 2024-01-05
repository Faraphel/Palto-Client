package com.example.palto.ui.session

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.palto.data.repository.AttendanceRepository
import com.example.palto.domain.Card

/**
 * ViewModel of a session which has a list of attendances.
 */
class SessionViewModel(
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {

    private val _cards: MutableLiveData<List<Card>> = MutableLiveData(emptyList())
    val cards = _cards as LiveData<List<Card>>

    fun insertCard(tag: Tag) {
        val card = Card(
            "0",
            tag.id,
            "tmp",
            "tmp"
        )
        _cards.value = (_cards.value ?: emptyList()) + card
        Log.d("NFC", "view model: A card has been had to the list")
    }

    /**
     *  ViewModel Factory.
     */
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {
                return SessionViewModel(
                    AttendanceRepository()
                ) as T
            }
        }
    }
}
