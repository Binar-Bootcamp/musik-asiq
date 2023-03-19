package com.binaracademy.musikasiq.data.repository.local

import androidx.lifecycle.LiveData
import com.binaracademy.musikasiq.data.room.Example

interface ExampleRepository {
    suspend fun createExample(example: Example)
}
