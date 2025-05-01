package com.amlan.rapidsplit.data.repository

import com.amlan.rapidsplit.data.local.db.entity.SplitEntity

interface SplitRepository {
    suspend fun saveSplit(entry: SplitEntity)
    suspend fun getAllSplits(): List<SplitEntity>
}
