package com.example.palto.ui

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.palto.domain.Card
import com.example.palto.domain.User

/**
 * CardViewModel maintain a list of cards application wide.
 * May be converted in a repository.
 */
class CardViewModel: ViewModel() {

    private var _cards = MutableLiveData<List<Card>>()
    private val cards: LiveData<List<Card>> = _cards

    fun createCard(user: User, tagId: String): Card {
        val list = _cards.value ?: emptyList()
        val card = Card(list.size, tagId, user)
        _cards.value = list + card
        Log.d("Palto", "CardViewModel: a card has been added into the list.")
        return card
    }

    fun getCard(tagId: String): Card? {
        val card = _cards.value?.find() {
            it.tagId == tagId
        }
        return card
    }
}