package com.binaracademy.musikasiq.data.model
import com.google.gson.annotations.SerializedName

data class PopularTrackResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("permalinkUrl")
    val permalinkUrl: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("tracks")
    val tracks: Tracks
)

