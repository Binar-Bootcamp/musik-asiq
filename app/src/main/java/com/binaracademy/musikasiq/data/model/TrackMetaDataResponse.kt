package com.binaracademy.musikasiq.data.model

import com.google.gson.annotations.SerializedName

data class TrackMetaDataResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("artworkUrl")
    val artworkUrl: String,
    @SerializedName("audio")
    val audio: List<Audio>,
    @SerializedName("caption")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("durationMs")
    val durationMs: Int,
    @SerializedName("durationText")
    val durationText: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("labelName")
    val labelName: String,
    @SerializedName("lastModified")
    val lastModified: String,
    @SerializedName("license")
    val license: String,
    @SerializedName("permalink")
    val permalink: String,
)

