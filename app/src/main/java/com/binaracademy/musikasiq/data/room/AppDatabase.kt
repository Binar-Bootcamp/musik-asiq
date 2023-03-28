package com.binaracademy.musikasiq.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.binaracademy.musikasiq.data.dao.ExampleDao
import com.binaracademy.musikasiq.data.dao.FavoriteDao
import com.binaracademy.musikasiq.data.dao.UserDao
import com.binaracademy.musikasiq.data.room.converters.DateConverters
import com.binaracademy.musikasiq.data.room.converters.TrackTypeConverters

@Database(entities = [(Example::class), (User::class), (Favorite::class)], version = 4)
@TypeConverters(DateConverters::class, TrackTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exampleDao(): ExampleDao

    abstract fun userDao(): UserDao

    abstract fun favoriteDao(): FavoriteDao

}