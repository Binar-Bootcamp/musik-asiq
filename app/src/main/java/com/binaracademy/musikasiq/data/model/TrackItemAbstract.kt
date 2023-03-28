package com.binaracademy.musikasiq.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class TrackItemAbstract(
    @Transient
    open var thumbnail: String? = "",
    @Transient
    open var title: String = "",
    @Transient
    open var description: String? = "",
): Parcelable