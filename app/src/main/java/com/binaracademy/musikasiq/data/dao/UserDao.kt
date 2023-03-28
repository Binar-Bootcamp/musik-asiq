package com.binaracademy.musikasiq.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binaracademy.musikasiq.data.room.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun register(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    fun login(email: String, password: String): User?

}