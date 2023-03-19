package com.binaracademy.musikasiq.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example")
data class Example(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String
)
