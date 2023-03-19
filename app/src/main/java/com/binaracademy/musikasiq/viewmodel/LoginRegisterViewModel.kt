package com.binaracademy.musikasiq.viewmodel

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

    var registerSuccess = MutableLiveData<User>()
    var registerError: String? = ""
    private val loginResult = MutableLiveData<Result<User>>()

    fun register(user: User) {
        viewModelScope.launch {
            try {
                userRepository.register(user)
                registerSuccess.value = user
            } catch (e: Exception) {
                registerError = e.message
            }
        }
    }
}