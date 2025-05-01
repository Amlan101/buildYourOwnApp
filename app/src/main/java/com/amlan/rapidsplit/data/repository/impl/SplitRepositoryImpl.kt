package com.amlan.rapidsplit.data.repository.impl

import com.amlan.rapidsplit.data.local.db.dao.SplitDao
import com.amlan.rapidsplit.data.local.db.entity.SplitEntity
import com.amlan.rapidsplit.data.repository.SplitRepository

class SplitRepositoryImpl(
    private val dao: SplitDao
) : SplitRepository {
    override suspend fun saveSplit(entry: SplitEntity) {
        dao.insertSplit(entry)
    }

    override suspend fun getAllSplits(): List<SplitEntity> {
        return dao.getAllSplits()
    }
}
