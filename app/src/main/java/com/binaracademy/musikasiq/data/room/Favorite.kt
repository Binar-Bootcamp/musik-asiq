package com.binaracademy.musikasiq.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.data.model.TrackItemOffline

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var track: TrackItem? = null,
    var offline: TrackItemOffline? = null
)
