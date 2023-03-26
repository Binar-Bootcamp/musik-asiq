package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.model.TrackMetaDataResponse
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepository
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepositoryImpl
import kotlinx.coroutines.launch

class MediaPlayerViewModel(
    private val soundCloudRepository: SoundCloudRepository = SoundCloudRepositoryImpl()
): ViewModel() {
    private val urlMusicToPlay = MutableLiveData<Result<TrackMetaDataResponse>>()

    fun getUrlToPlay(): LiveData<Result<TrackMetaDataResponse>> = urlMusicToPlay

    fun loadTrackMetaData(id: String) {
        viewModelScope.launch {
            val result = soundCloudRepository.getTrackMetaData(id)
            urlMusicToPlay.value = result
        }
    }
}