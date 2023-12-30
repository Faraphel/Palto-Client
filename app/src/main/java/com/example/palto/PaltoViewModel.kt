package com.example.palto

import android.nfc.Tag
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaltoViewModel: ViewModel() {
    val tagLiveData = MutableLiveData<Tag>()
}