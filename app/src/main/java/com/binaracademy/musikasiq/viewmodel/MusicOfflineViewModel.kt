package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepository
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepositoryImpl
import com.binaracademy.musikasiq.data.room.Favorite
import kotlinx.coroutines.launch

class MusicOfflineViewModel(
    private val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl()
): ViewModel() {

    private val favorite = MutableLiveData<Result<Favorite>>()

    fun getFavorite(): LiveData<Result<Favorite>> = favorite

    fun clickFavorite(payload: Favorite) {
        viewModelScope.launch {
            favorite.value = favoriteRepository.saveFavorite(payload)
        }
    }
}