package com.binaracademy.musikasiq.data.room.converters

import androidx.room.TypeConverter
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.data.model.TrackItemAbstract
import com.binaracademy.musikasiq.data.model.TrackItemOffline
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TrackTypeConverters {
    @TypeConverter
    fun toTrackItem(trackItem: String?): TrackItem? {
        if (trackItem == null) return null
        return Gson().fromJson(trackItem, TrackItem::class.java)
    }

    @TypeConverter
    fun toTrackItemJson(trackItem: TrackItem?):String{
        val type=object :TypeToken<TrackItem>(){}.type
        return Gson().toJson(trackItem, type)
    }

    @TypeConverter
    fun toTrackItemOffline(trackItem: String?): TrackItemOffline? {
        if (trackItem == null) return null
        return Gson().fromJson(trackItem, TrackItemOffline::class.java)
    }

    @TypeConverter
    fun toTrackItemOfflineJson(trackItem: TrackItemOffline?):String{
        val type=object :TypeToken<TrackItemOffline>(){}.type
        return Gson().toJson(trackItem, type)
    }

    @TypeConverter
    fun toTrackItem(track: TrackItemAbstract?) : TrackItem? {
        if (track is TrackItem) {
            return track
        }
        return null
    }

    @TypeConverter
    fun toTrackItemOffline(track: TrackItemAbstract?) : TrackItemOffline? {
        if (track is TrackItemOffline) {
            return track
        }
        return null
    }
}