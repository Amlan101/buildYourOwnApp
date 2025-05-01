package com.amlan.rapidsplit.domain.usecase

import com.amlan.rapidsplit.data.local.db.entity.SplitEntity
import com.amlan.rapidsplit.data.repository.SplitRepository

class SaveSplitUseCase(
    private val repository: SplitRepository
) {
    suspend fun execute(entry: SplitEntity) = repository.saveSplit(entry)
}
