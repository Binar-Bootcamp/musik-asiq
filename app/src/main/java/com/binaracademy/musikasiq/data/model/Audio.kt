package com.binaracademy.musikasiq.data.model

import com.google.gson.annotations.SerializedName

data class Audio(
    @SerializedName("durationMs")
    val durationMs: Int,
    @SerializedName("durationText")
    val durationText: String,
    @SerializedName("extension")
    val extension: String,
    @SerializedName("mimeType")
    val mimeType: String,
    @SerializedName("quality")
    val quality: String,
    @SerializedName("url")
    val url: String
)