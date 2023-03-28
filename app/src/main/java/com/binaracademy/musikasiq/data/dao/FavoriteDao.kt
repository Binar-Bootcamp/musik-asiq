package com.binaracademy.musikasiq.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.data.model.TrackItemAbstract
import com.binaracademy.musikasiq.data.model.TrackItemOffline
import com.binaracademy.musikasiq.data.room.Favorite

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrackFavorite(favorite: Favorite)

    @Query("SELECT favorites.track FROM favorites WHERE track IS NOT NULL")
    fun getOnlineFavorites(): LiveData<List<TrackItem?>>

    @Query("SELECT favorites.offline FROM favorites WHERE offline IS NOT NULL")
    fun getOfflineFavorites(): LiveData<List<TrackItemOffline?>>

    @Query("DELETE FROM favorites WHERE favorites.track = :track OR favorites.offline = :track")
    fun deleteByTrack(track: TrackItemAbstract)

}