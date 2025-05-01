package com.amlan.rapidsplit.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "split_history")
data class SplitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val amount: Double,
    val vehicleType: String,
    val start: String,
    val destination: String,
    val timestamp: Long
)

