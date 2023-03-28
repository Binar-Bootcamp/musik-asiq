package com.binaracademy.musikasiq.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.repository.local.UserRepository
import com.binaracademy.musikasiq.data.repository.local.UserRepositoryImpl
import com.binaracademy.musikasiq.data.room.User
import kotlinx.coroutines.launch

class LoginRegisterViewModel(
    private val userRepository: UserRepository = UserRepositoryImpl()
) : ViewModel() {

    var registerResult = MutableLiveData<Result<User>>()
    var loginResult = MutableLiveData<Result<User>>()

    fun register(user: User) {
        viewModelScope.launch {
            try {
                userRepository.register(user)
                registerResult.value = Result.success(user)
            } catch (e: Exception) {
                registerResult.value = Result.failure(e)
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = userRepository.login(email, password)
                if (user != null) {
                    loginResult.value = Result.success(user)
                    return@launch
                }

                loginResult.value = Result.failure(Exception("user doesn't exist"))
            } catch (e: Exception) {
                Log.i("Info Result", e.toString())
                loginResult.value = Result.failure(e)
            }
        }
    }
}