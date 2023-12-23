package com.example.palto.ui.attendanceList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.palto.data.network.ServerDataSource
import com.example.palto.data.repository.AttendanceRepository
import com.example.palto.model.Card
import kotlin.random.Random

class AttendanceListViewModel(
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {

    private val cardsList: MutableList<Card> = mutableListOf()
    val cardsLiveData: LiveData<List<Card>> = MutableLiveData(cardsList)

    fun insertCard(cardId: String) {
        val card = Card(cardId)
        cardsList.add(card)
    }
}

class AttendanceListViewModelFactory : ViewModelProvider.Factory {

    //@Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttendanceListViewModel::class.java)) {
            return AttendanceListViewModel(
                attendanceRepository = AttendanceRepository(
                    dataSource = ServerDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}