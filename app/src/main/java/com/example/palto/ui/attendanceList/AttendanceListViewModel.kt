package com.example.palto.ui.attendanceList

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.palto.data.network.ServerDataSource
import com.example.palto.data.repository.AttendanceRepository
import com.example.palto.model.Card

/**
 * ViewModel of a session which has a list of attendances.
 */
class AttendanceListViewModel(
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {

    val cardsLiveData: MutableLiveData<List<Card>> = MutableLiveData(emptyList())

    fun insertCard(tag: Tag) {
        val card = Card(
            "0",
            tag.id,
            "tmp",
            "tmp"
        )
        cardsLiveData.value = (cardsLiveData.value ?: emptyList()) + card
        Log.d("NFC", "view model: A card has been had to the list")
    }

    /**
     * ViewModel Factory.
     */
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {
                return AttendanceListViewModel(
                    AttendanceRepository(ServerDataSource())
                ) as T
            }
        }
    }
}
