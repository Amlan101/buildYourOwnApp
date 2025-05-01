package com.amlan.rapidsplit.ui.presentation.shared

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.amlan.rapidsplit.domain.model.Ride

class RideSharedViewModel : ViewModel() {
    // Use a mutableStateOf that's observable by Compose
    var confirmedRide = mutableStateOf<Ride?>(null)
        private set

    // Function to update the ride safely
    fun setConfirmedRide(ride: Ride?) {
        confirmedRide.value = ride
    }
}
