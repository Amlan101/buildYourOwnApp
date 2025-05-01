package com.amlan.rapidsplit.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amlan.rapidsplit.data.local.db.dao.SplitDao
import com.amlan.rapidsplit.data.local.db.entity.SplitEntity

@Database(entities = [SplitEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun splitDao(): SplitDao
}
