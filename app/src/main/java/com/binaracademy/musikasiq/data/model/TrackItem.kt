package com.binaracademy.musikasiq.data.model

import com.google.gson.annotations.SerializedName

data class TrackItem(
    @SerializedName("artworkUrl")
    val artworkUrl: String?,
    @SerializedName("caption")
    val caption: Any,
    @SerializedName("commentCount")
    val commentCount: Int,
    @SerializedName("commentable")
    val commentable: Boolean,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: Any,
    @SerializedName("durationMs")
    val durationMs: Int,
    @SerializedName("durationText")
    val durationText: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("goPlus")
    val goPlus: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("labelName")
    val labelName: String,
    @SerializedName("lastModified")
    val lastModified: String,
    @SerializedName("license")
    val license: String,
    @SerializedName("likeCount")
    val likeCount: Int,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("playCount")
    val playCount: Int,
    @SerializedName("purchaseTitle")
    val purchaseTitle: Any,
    @SerializedName("purchaseUrl")
    val purchaseUrl: Any,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("repostCount")
    val repostCount: Int,
    @SerializedName("stationPermalink")
    val stationPermalink: String,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("visuals")
    val visuals: List<Any>,
    @SerializedName("waveformUrl")
    val waveformUrl: String
)