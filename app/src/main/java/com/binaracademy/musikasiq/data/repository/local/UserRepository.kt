package com.binaracademy.musikasiq.data.repository.local

import com.binaracademy.musikasiq.data.room.Example
import com.binaracademy.musikasiq.data.room.User

interface UserRepository {
    suspend fun register(user: User)

    suspend fun login(email: String, password: String)
}
