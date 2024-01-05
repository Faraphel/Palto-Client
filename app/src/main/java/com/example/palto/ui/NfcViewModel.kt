package com.example.palto.ui

import android.nfc.Tag
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.palto.domain.User

class NfcViewModel: ViewModel() {
    val tag = MutableLiveData<Tag>()

    private var _user : MutableLiveData<User> = MutableLiveData<User>()
    val user : LiveData<User> = _user
}