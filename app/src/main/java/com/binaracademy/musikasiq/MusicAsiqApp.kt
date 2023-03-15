package com.binaracademy.musikasiq

import android.app.Application
import androidx.room.Room
import com.binaracademy.musikasiq.data.room.AppDatabase

class MusicAsiqApp: Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}