package com.binaracademy.musikasiq.data.model.api

data class Failure(val error: Throwable): Result<Nothing>()