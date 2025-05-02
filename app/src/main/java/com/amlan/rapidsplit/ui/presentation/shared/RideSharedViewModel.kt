package com.amlan.rapidsplit.ui.presentation.shared

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.amlan.rapidsplit.domain.model.Ride

class RideSharedViewModel : ViewModel() {
    var confirmedRide = mutableStateOf<Ride?>(null)
        private set

    fun setConfirmedRide(ride: Ride?) {
        confirmedRide.value = ride
    }
}
