package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.*
import com.binaracademy.musikasiq.data.model.TrackItemAbstract
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepository
import com.binaracademy.musikasiq.data.repository.local.FavoriteRepositoryImpl
import com.binaracademy.musikasiq.data.room.Favorite
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl()
) : ViewModel() {

    private val favorites = MutableLiveData<List<TrackItemAbstract?>>()

    fun getFavorites(): LiveData<List<TrackItemAbstract?>> = favorites

    fun loadFavorites(lifecycleOwner: LifecycleOwner){
        val fromLocal = favoriteRepository.getFavorites()
        fromLocal.observe(lifecycleOwner) { it ->
            val mapped = it.map { favorite: Favorite ->
                if (favorite.track != null) favorite.track else favorite.offline
            }

            favorites.value = mapped
        }
    }

    fun deleteFavorite(track: TrackItemAbstract){
        viewModelScope.launch {
            favoriteRepository.deleteByTrack(track)
        }
    }
}