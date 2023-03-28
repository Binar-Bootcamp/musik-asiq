package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.model.TrackMetaDataResponse
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepository
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepositoryImpl
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepository
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepositoryImpl
import com.binaracademy.musikasiq.data.room.Favorite
import kotlinx.coroutines.launch

class MediaPlayerViewModel(
    private val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl(),
    private val soundCloudRepository: SoundCloudRepository = SoundCloudRepositoryImpl()
) : ViewModel() {
    private val urlMusicToPlay = MutableLiveData<Result<TrackMetaDataResponse>>()
    private val favorite = MutableLiveData<Result<Favorite>>()

    fun getUrlToPlay(): LiveData<Result<TrackMetaDataResponse>> = urlMusicToPlay
    fun getFavorite(): LiveData<Result<Favorite>> = favorite

    fun loadTrackMetaData(id: String) {
        viewModelScope.launch {
            val result = soundCloudRepository.getTrackMetaData(id)
            urlMusicToPlay.value = result
        }
    }

    fun clickFavorite(payload: Favorite) {
        viewModelScope.launch {
            favorite.value = favoriteRepository.saveFavorite(payload)
        }
    }
}