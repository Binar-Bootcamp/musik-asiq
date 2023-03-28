package com.binaracademy.musikasiq.data.repository.local

import androidx.lifecycle.LiveData
import com.binaracademy.musikasiq.MusicAsiqApp
import com.binaracademy.musikasiq.data.dao.FavoriteDao
import com.binaracademy.musikasiq.data.model.TrackItemAbstract
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

    override fun getOnlineFavorites(): Result<LiveData<List<TrackItemAbstract?>>> {
        return try {
            val onlineFavorites = favoriteDao.getOnlineFavorites()
            Result.success(onlineFavorites as LiveData<List<TrackItemAbstract?>>)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override fun getOfflineFavorites(): Result<LiveData<List<TrackItemAbstract?>>> {
        return try {
            val offlineFavorites = favoriteDao.getOfflineFavorites()
            Result.success(offlineFavorites as LiveData<List<TrackItemAbstract?>>)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun deleteByTrack(track: TrackItemAbstract) {
        withContext(Dispatchers.IO) {
            favoriteDao.deleteByTrack(track)
        }
    }
}