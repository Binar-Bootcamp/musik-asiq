package com.binaracademy.musikasiq.data.repository.local

import com.binaracademy.musikasiq.MusicAsiqApp
import com.binaracademy.musikasiq.data.dao.ExampleDao
import com.binaracademy.musikasiq.data.room.Example
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExampleRepositoryImpl : ExampleRepository {
    private val exampleDao: ExampleDao = MusicAsiqApp.database.exampleDao()

    override suspend fun createExample(example: Example) {
        withContext(Dispatchers.IO) {
            exampleDao.insertExample(example)
        }
    }
}