package com.binaracademy.musikasiq.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.binaracademy.musikasiq.data.dao.ExampleDao
import com.binaracademy.musikasiq.data.room.converters.DateConverters

@Database(entities = [(Example::class)], version = 0)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exampleDao(): ExampleDao

}