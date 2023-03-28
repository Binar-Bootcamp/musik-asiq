package com.binaracademy.musikasiq.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrackItemOffline(
    @SerializedName("thumbnail")
    override var thumbnail: String? = "",

    @SerializedName("title")
    override var title: String = "",

    @SerializedName("description")
    override var description: String? = "",

    var songResId: Int = 0
): TrackItemAbstract(), Parcelable
