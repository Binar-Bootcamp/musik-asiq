package com.binaracademy.musikasiq.data.repository.remote

import com.binaracademy.musikasiq.data.api.RetrofitClient
import com.binaracademy.musikasiq.data.api.UserService
import com.binaracademy.musikasiq.data.model.User

class UserRepositoryImpl(
    private val client: RetrofitClient
): UserRepository {

    private val userService: UserService = client.instance.create(UserService::class.java)

    override suspend fun getAllUser(): Result<User> {
        return try {
            val user = userService.getAllUsers()
            Result.success(user)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}