package com.example.palto.ui.session

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.palto.R
import com.example.palto.domain.Attendance
import com.example.palto.domain.Event
import com.example.palto.domain.Session
import com.example.palto.domain.User
import java.time.LocalTime

/**
 * ViewModel of a session which has a list of attendances.
 */
class SessionViewModel() : ViewModel() {

    private var _result = MutableLiveData<Event<SessionResult>>()
    val result: LiveData<Event<SessionResult>> = _result

    private var _attendances = MutableLiveData<List<Attendance>>()
    val attendances: LiveData<List<Attendance>> = _attendances

    // The opened session which have been selected in the menu.
    var session: Session? = null

    /**
     * Add the [student] in the attendance list [attendances].
     * Return true if it has been added, else return false.
     */
    fun addAttendance(student: User) {
        val list = _attendances.value ?: emptyList()

        // If the list already contains the user, return false
        if (list.any { it.student == student }) {
            Log.d("Palto", "SessionViewModel: User already in the list.")
            _result.value = Event(SessionResult(
                false,
                R.string.session_user_already_added,
                student.username
            ))

        // Else create a new attendance and add it into the list.
        } else {
            val attendance = Attendance(
                id = list.size,
                student = student,
                date = LocalTime.now()
            )
            // Add the attendance in the attendance list, and trigger the observers.
            _attendances.value = list + attendance
            // Saved the list in the session.
            session?.attendances = list + attendance

            _result.value = Event(SessionResult(
                true,
                R.string.session_user_added,
                student.username
            ))
            Log.d("Palto", "SessionViewModel: An attendance has been added into the list.")
        }
    }

    fun setAttendanceList(list: List<Attendance>) {
        _attendances.value = list
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
                return SessionViewModel() as T
            }
        }
    }
}
