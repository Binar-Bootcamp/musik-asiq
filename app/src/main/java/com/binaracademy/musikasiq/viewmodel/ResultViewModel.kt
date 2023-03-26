package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.model.SearchTrackResponse
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepository
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepositoryImpl
import kotlinx.coroutines.launch

class ResultViewModel(
    private val soundCloudRepository: SoundCloudRepository = SoundCloudRepositoryImpl()
): ViewModel() {
    private val trackResult = MutableLiveData<Result<SearchTrackResponse>>()

    fun getTrackResult(): LiveData<Result<SearchTrackResponse>> = trackResult

    fun searchTrack(term: String) {
        viewModelScope.launch {
            trackResult.value = soundCloudRepository.searchTrackByUser(term)
        }
    }
}