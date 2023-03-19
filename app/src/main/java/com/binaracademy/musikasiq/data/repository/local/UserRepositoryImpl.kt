package com.binaracademy.musikasiq.data.repository.local

import com.binaracademy.musikasiq.MusicAsiqApp
import com.binaracademy.musikasiq.data.dao.UserDao
import com.binaracademy.musikasiq.data.room.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl : UserRepository {

    private val userDao: UserDao = MusicAsiqApp.database.userDao()

    override suspend fun register(user: User) {
        withContext(Dispatchers.IO) {
            userDao.register(user)
        }
    }

    override suspend fun login(email: String, password: String) {
        withContext(Dispatchers.IO) {
            userDao.login(email, password)
        }
    }
}