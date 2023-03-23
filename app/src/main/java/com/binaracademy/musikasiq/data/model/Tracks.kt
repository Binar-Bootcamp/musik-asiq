package com.binaracademy.musikasiq.data.model

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("items")
    val items: List<TrackItem>,
    @SerializedName("nextOffset")
    val nextOffset: Any
)