package com.binaracademy.musikasiq.data.model
import com.google.gson.annotations.SerializedName


data class ListTrackResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("tracks")
    val tracks: Tracks,
    @SerializedName("type")
    val type: String
)
