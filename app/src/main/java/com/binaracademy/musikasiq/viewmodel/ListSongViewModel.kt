package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.model.ListTrackResponse
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepository
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepositoryImpl
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepository
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepositoryImpl
import com.binaracademy.musikasiq.data.room.Favorite
import kotlinx.coroutines.launch

class ListSongViewModel(
    private val soundCloudRepository: SoundCloudRepository = SoundCloudRepositoryImpl(),
    private val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl()
): ViewModel() {
    private val tracks = MutableLiveData<Result<ListTrackResponse>>()

    private val favorite = MutableLiveData<Result<Favorite>>()

    fun getTracks(): LiveData<Result<ListTrackResponse>> = tracks

    fun getFavorite(): LiveData<Result<Favorite>> = favorite

    fun loadTracks() {
        viewModelScope.launch {
            tracks.value = soundCloudRepository.getTracks()
        }
    }

    fun clickFavorite(payload: Favorite) {
        viewModelScope.launch {
            favorite.value = favoriteRepository.saveFavorite(payload)
        }
    }
}