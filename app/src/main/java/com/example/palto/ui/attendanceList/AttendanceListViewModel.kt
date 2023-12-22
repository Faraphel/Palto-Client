package com.example.palto.ui.attendanceList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.palto.data.network.ServerDataSource
import com.example.palto.data.repository.AttendanceRepository
import com.example.palto.data.repository.LoginRepository

class AttendanceListViewModel(
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {
    /*
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult
    */

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
}