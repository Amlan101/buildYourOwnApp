package com.amlan.rapidsplit.domain.usecase

import com.amlan.rapidsplit.domain.model.Ride
import com.amlan.rapidsplit.domain.model.VehicleType

class CalculateEstimateUseCase {
    operator fun invoke(start: String, end: String, vehicle: VehicleType): Ride {

        val baseFare = when (vehicle) {
            VehicleType.BIKE -> 20.0
            VehicleType.AUTO -> 30.0
            VehicleType.CAR -> 50.0
        }
        val time = (5..15).random()
        return Ride(start, end, vehicle, baseFare + time * 2, time)
    }
}
