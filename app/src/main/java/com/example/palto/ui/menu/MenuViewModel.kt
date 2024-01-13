package com.example.palto.ui.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.palto.domain.Attendance
import com.example.palto.domain.Session

/**
 * ViewModel for accessing all the sessions created.
 */
class MenuViewModel() : ViewModel() {

    // A list of sessions.
    private var _sessions = MutableLiveData<List<Session>>()
    val sessions: LiveData<List<Session>> = _sessions

    fun createSession(name: String) {
        val list = _sessions.value ?: emptyList()
        val session = Session(
            id = list.size,
            name = name,
            attendances = emptyList()
        )
        _sessions.value = list + session
        Log.d("Palto", "MenuViewModel: A session has been added into the list.")
    }

    fun getSession(id: Int): Session? {
        return _sessions.value?.find { it.id == id }
    }

    fun setAttendanceListSession(id: Int, list: List<Attendance>) {
        getSession(id)?.let { it.attendances = list }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MenuViewModel() as T
            }
        }
    }
}