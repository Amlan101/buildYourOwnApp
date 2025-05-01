package com.amlan.rapidsplit.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amlan.rapidsplit.data.local.db.entity.SplitEntity

@Dao
interface SplitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSplit(entry: SplitEntity)

    @Query("SELECT * FROM split_history ORDER BY timestamp DESC")
    suspend fun getAllSplits(): List<SplitEntity>
}
