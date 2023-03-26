package com.binaracademy.musikasiq.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrackItemOffline(
    var thumbnail: Int = 0,

    var title: String = "",

    var description: String = "",

    var songResId: Int = 0
): TrackItemAbstract(), Parcelable
