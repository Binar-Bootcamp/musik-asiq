package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binaracademy.musikasiq.data.repository.local.ExampleRepository
import com.binaracademy.musikasiq.data.repository.local.ExampleRepositoryImpl

class HomeViewModel(
    private val exampleRepository: ExampleRepository = ExampleRepositoryImpl()
): ViewModel() {
    private val creatureLiveData = MutableLiveData<String>()
    private val saveLiveData = MutableLiveData<Boolean>()

    fun getCreatureLiveData(): LiveData<String> = creatureLiveData
    fun getSaveLiveData(): LiveData<Boolean> = saveLiveData

    var intelligence = 0
    var strength = 0
    var endurance = 0
}