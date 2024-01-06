package com.example.palto.ui.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.palto.domain.Session

class MenuViewModel() : ViewModel() {

    private var _sessions = MutableLiveData<List<Session>>(emptyList())
    val sessions = _sessions as LiveData<List<Session>>

    fun createSession() {
        val session = Session(
            id = "aahh"
        )
        _sessions.value = (_sessions.value ?: emptyList()) + session
        Log.d("PALTO", "MenuViewModel: a session has been added into the list.")
    }
}