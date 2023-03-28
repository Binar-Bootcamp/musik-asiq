package com.binaracademy.musikasiq.data.repository.local

import androidx.lifecycle.LiveData
import com.binaracademy.musikasiq.data.model.TrackItemAbstract
import com.binaracademy.musikasiq.data.room.Favorite

interface FavoriteRepository {

    suspend fun saveFavorite(favorite: Favorite): Result<Favorite>

    fun getFavorites(): LiveData<List<Favorite>>

    suspend fun deleteByTrack(track: TrackItemAbstract)

}