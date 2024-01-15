package com.example.palto.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.palto.domain.Card
import com.example.palto.domain.User

/**
 * UserViewModel maintain a list of users application wide.
 * May be converted into a repository.
 */
class UserViewModel: ViewModel() {

    private var _users = MutableLiveData<List<User>>()
    val users : LiveData<List<User>> = _users

    fun createUser(username: String): User {
        val list = _users.value ?: emptyList()
        val user = User(
            id = list.size,
            username = username,
            firstName = "",
            lastName = "",
            email = "")
        _users.value = list + user
        Log.d("Palto", "UserViewModel: a user has been added into the list.")
        return user
    }
}