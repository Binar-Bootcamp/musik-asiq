package com.binaracademy.musikasiq.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class TrackItem(
    @SerializedName("artworkUrl")
    val artworkUrl: String?,
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("commentCount")
    val commentCount: Int,
    @SerializedName("commentable")
    val commentable: Boolean,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String?,
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
    val purchaseTitle: String?,
    @SerializedName("purchaseUrl")
    val purchaseUrl: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("repostCount")
    val repostCount: Int,
    @SerializedName("stationPermalink")
    val stationPermalink: String,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("visuals")
    val visuals: @RawValue List<Any>,
    @SerializedName("waveformUrl")
    val waveformUrl: String
) : Parcelable