package com.example.palto.ui.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.palto.data.repository.TokenRepository
import com.example.palto.data.repository.UserRepository
import com.example.palto.domain.Session
import com.example.palto.ui.UserViewModel

class MenuViewModel() : ViewModel() {

    private var _sessions = MutableLiveData<List<Session>>(emptyList())
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

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MenuViewModel() as T
            }
        }
    }
}