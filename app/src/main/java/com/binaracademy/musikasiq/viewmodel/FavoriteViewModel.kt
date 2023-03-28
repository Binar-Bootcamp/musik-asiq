package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.model.TrackItemAbstract
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepository
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepositoryImpl
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl()
) : ViewModel() {

    fun loadOnlineFavorite(): Result<LiveData<List<TrackItemAbstract?>>> {
        return favoriteRepository.getOnlineFavorites()
    }

    fun loadOfflineFavorite(): Result<LiveData<List<TrackItemAbstract?>>> {
        return favoriteRepository.getOfflineFavorites()
    }

    fun deleteFavorite(track: TrackItemAbstract){
        viewModelScope.launch {
            favoriteRepository.deleteByTrack(track)
        }
    }
}