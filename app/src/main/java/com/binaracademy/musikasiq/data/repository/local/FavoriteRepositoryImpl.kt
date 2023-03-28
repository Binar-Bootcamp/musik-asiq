package com.binaracademy.musikasiq.data.repository.local

import androidx.lifecycle.LiveData
import com.binaracademy.musikasiq.MusicAsiqApp
import com.binaracademy.musikasiq.data.dao.FavoriteDao
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.data.model.TrackItemAbstract
import com.binaracademy.musikasiq.data.model.TrackItemOffline
import com.binaracademy.musikasiq.data.room.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepositoryImpl : FavoriteRepository {

    private val favoriteDao: FavoriteDao = MusicAsiqApp.database.favoriteDao()

    override suspend fun saveFavorite(favorite: Favorite): Result<Favorite> {
        return withContext(Dispatchers.IO) {
            try {
                favoriteDao.insertTrackFavorite(favorite)
                Result.success(favorite)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun deleteByTrack(track: TrackItemAbstract) {
        withContext(Dispatchers.IO) {
            if (track is TrackItemOffline){
                favoriteDao.deleteByTrackOffline(track)
            }

            if (track is TrackItem){
                favoriteDao.deleteByTrackOnline(track)
            }
        }
    }

    override fun getFavorites(): LiveData<List<Favorite>> {
        return favoriteDao.getFavorites()
    }
}