package com.amlan.rapidsplit.ui.presentation.history

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlan.rapidsplit.data.local.db.entity.SplitEntity
import com.amlan.rapidsplit.domain.usecase.GetSplitHistoryUseCase
import kotlinx.coroutines.launch

class SplitHistoryViewModel(
    private val getSplitHistoryUseCase: GetSplitHistoryUseCase
) : ViewModel() {

    val splitList: SnapshotStateList<SplitEntity> = mutableStateListOf()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            val splits = getSplitHistoryUseCase.execute()
            splitList.clear()
            splitList.addAll(splits)
        }
    }
}
