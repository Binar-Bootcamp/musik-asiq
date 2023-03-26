package com.binaracademy.musikasiq.data.model.dummy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
	var name: String = "",
	var artist: String = "",
	var description: String = "",
	var photo: Int = 0,
	var videoUrl: String = ""
) : Parcelable
