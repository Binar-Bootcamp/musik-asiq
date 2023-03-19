package com.binaracademy.musikasiq.data.model.api

data class Success<out T: Any>(val data: T): Result<T>()