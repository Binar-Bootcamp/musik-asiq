package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.model.ListTrackResponse
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepository
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepositoryImpl
import kotlinx.coroutines.launch

class ListSongViewModel(
    private val soundCloudRepository: SoundCloudRepository = SoundCloudRepositoryImpl()
): ViewModel() {
    private val tracks = MutableLiveData<Result<ListTrackResponse>>()

    fun getTracks(): LiveData<Result<ListTrackResponse>> = tracks

    fun loadTracks() {
        viewModelScope.launch {
            tracks.value = soundCloudRepository.getTracks()
        }
    }
}