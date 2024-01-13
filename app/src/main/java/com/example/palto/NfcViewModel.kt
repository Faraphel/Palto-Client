package com.example.palto

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.palto.domain.Card
import com.example.palto.domain.Event
import com.example.palto.domain.User

class NfcViewModel: ViewModel() {

    private var _tagId = MutableLiveData<Event<String>>()
    val tagId: LiveData<Event<String>> = _tagId

    @OptIn(ExperimentalStdlibApi::class)
    fun setTag(tag: Tag) {
        Log.d("Nfc", "A new tag has been set.")
        _tagId.postValue(Event(tag.id.toHexString()))
    }
}