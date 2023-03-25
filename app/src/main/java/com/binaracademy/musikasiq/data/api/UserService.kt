package com.binaracademy.musikasiq.data.api

import com.binaracademy.musikasiq.data.model.User
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getAllUsers(): User
}