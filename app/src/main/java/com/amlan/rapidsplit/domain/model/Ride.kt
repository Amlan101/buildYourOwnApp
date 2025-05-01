package com.amlan.rapidsplit.domain.model

data class Ride(
    val startLocation: String,
    val destination: String,
    val vehicleType: VehicleType,
    val estimatedPrice: Double,
    val estimatedTime: Int
)

enum class VehicleType {
    BIKE, AUTO, CAR
}
