package com.amlan.rapidsplit.ui.presentation.fuelsplit

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlan.rapidsplit.data.local.db.entity.SplitEntity
import com.amlan.rapidsplit.domain.usecase.CalculateSplitUseCase
import com.amlan.rapidsplit.domain.usecase.SaveSplitUseCase
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel
import kotlinx.coroutines.launch

class FuelSplitViewModel(
    private val calculateSplitUseCase: CalculateSplitUseCase,
    private val saveSplitUseCase: SaveSplitUseCase,
    private val rideSharedViewModel: RideSharedViewModel
) : ViewModel() {

    var friendName by mutableStateOf("")
    val friends = mutableStateListOf<String>()
    val splitList = mutableStateListOf<SplitEntity>()

    val ride get() = rideSharedViewModel.confirmedRide.value

    fun addFriend() {
        if (friendName.isNotBlank()) {
            friends.add(friendName.trim())
            friendName = ""
            calculateSplit()
        }
    }

    private fun calculateSplit() {
        val ride = this.ride ?: return
        val splits = calculateSplitUseCase(
            totalAmount = ride.estimatedPrice,
            people = friends,
            vehicleType = ride.vehicleType,
            startLocation = ride.startLocation,
            destination = ride.destination
        )
        splitList.clear()
        splitList.addAll(splits)
    }

    fun saveSplits() {
        viewModelScope.launch {
            splitList.forEach { entry ->
                saveSplitUseCase.execute(entry)
            }
        }
    }
}
