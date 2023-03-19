package com.binaracademy.musikasiq.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var email: String,
    var password: String
)
