package com.binaracademy.musikasiq.data.model

import com.google.gson.annotations.SerializedName

data class SearchTrackResponse(
    @SerializedName("genreStats")
    val genreStats: List<GenreStat>,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("tracks")
    val tracks: Tracks
)

