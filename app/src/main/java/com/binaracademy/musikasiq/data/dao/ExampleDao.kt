package com.binaracademy.musikasiq.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.binaracademy.musikasiq.data.room.Example

@Dao
interface ExampleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExample(summary: Example)

}