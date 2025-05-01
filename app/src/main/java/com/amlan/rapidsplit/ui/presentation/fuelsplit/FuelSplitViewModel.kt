package com.amlan.rapidsplit.ui.presentation.fuelsplit

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.amlan.rapidsplit.domain.model.SplitEntry
import com.amlan.rapidsplit.domain.usecase.CalculateSplitUseCase
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.amlan.rapidsplit.data.local.db.entity.SplitEntity
import com.amlan.rapidsplit.domain.usecase.SaveSplitUseCase
import kotlinx.coroutines.launch


class FuelSplitViewModel(
    private val calculateSplitUseCase: CalculateSplitUseCase,
    private val saveSplitUseCase: SaveSplitUseCase,
    private val rideSharedViewModel: RideSharedViewModel
): ViewModel() {
    var friendName by mutableStateOf("")
    val friends = mutableStateListOf<String>()
    val splitList = mutableStateListOf<SplitEntry>()

    val ride get() = rideSharedViewModel.confirmedRide.value

    fun addFriend() {
        if (friendName.isNotBlank()) {
            friends.add(friendName.trim())
            friendName = ""
            calculateSplit()
        }
    }

    private fun calculateSplit() {
        val total = ride?.estimatedPrice ?: 0.0
        splitList.clear()
        splitList.addAll(calculateSplitUseCase.execute(total, friends))
    }

    fun saveSplits() {
        val ride = this.ride ?: return
        viewModelScope.launch {
            splitList.forEach { entry ->
                saveSplitUseCase.execute(
                    SplitEntity(
                        name = entry.name,
                        amount = entry.amount,
                        vehicleType = ride.vehicleType.name,
                        start = ride.startLocation,
                        destination = ride.destination,
                        timestamp = System.currentTimeMillis()
                    )
                )
            }
        }
    }

}