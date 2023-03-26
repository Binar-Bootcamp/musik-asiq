package com.binaracademy.musikasiq.data.model

import com.google.gson.annotations.SerializedName

data class GenreStat(
    @SerializedName("count")
    val count: Int,
    @SerializedName("value")
    val value: String
)