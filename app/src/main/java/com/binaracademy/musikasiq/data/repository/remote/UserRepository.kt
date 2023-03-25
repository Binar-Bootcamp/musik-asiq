package com.binaracademy.musikasiq.data.repository.remote

import com.binaracademy.musikasiq.data.model.User

interface UserRepository {
    suspend fun getAllUser(): Result<User>
}