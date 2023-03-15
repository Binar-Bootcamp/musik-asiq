package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.repository.local.ExampleRepository
import com.binaracademy.musikasiq.data.repository.local.ExampleRepositoryImpl
import kotlinx.coroutines.launch

class ExampleViewModel(
    private val exampleRepository: ExampleRepository = ExampleRepositoryImpl()
): ViewModel() {

}