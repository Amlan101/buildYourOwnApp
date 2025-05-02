package com.amlan.rapidsplit.ui.presentation.ride_selection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.amlan.rapidsplit.domain.model.Ride
import com.amlan.rapidsplit.domain.model.VehicleType
import com.amlan.rapidsplit.domain.usecase.CalculateEstimateUseCase
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel

class RideSelectionViewModel(
    private val calculateEstimateUseCase: CalculateEstimateUseCase
) : ViewModel() {

    var startLocation by mutableStateOf("")
    var destination by mutableStateOf("")
    var selectedVehicle by mutableStateOf(VehicleType.BIKE)
    var rideEstimate by mutableStateOf<Ride?>(null)

    fun calculateEstimate() {
        if (startLocation.isNotBlank() && destination.isNotBlank()) {
            rideEstimate = calculateEstimateUseCase(startLocation, destination, selectedVehicle)
        }
    }
    fun confirmRide(sharedViewModel: RideSharedViewModel) {
        rideEstimate?.let {
            sharedViewModel.setConfirmedRide(it)
        }
    }
}
