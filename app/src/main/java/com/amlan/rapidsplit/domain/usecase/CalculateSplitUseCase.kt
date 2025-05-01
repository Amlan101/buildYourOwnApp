package com.amlan.rapidsplit.domain.usecase

import com.amlan.rapidsplit.data.local.db.entity.SplitEntity
import com.amlan.rapidsplit.domain.model.VehicleType

class CalculateSplitUseCase {
    operator fun invoke(
        totalAmount: Double,
        people: List<String>,
        vehicleType: VehicleType,
        startLocation: String,
        destination: String,
        roundTo: Int = 2
    ): List<SplitEntity> {
        require(totalAmount >= 0) { "Total amount must be positive" }
        require(people.isNotEmpty()) { "People list cannot be empty" }

        val perPerson = calculateEqualSplit(totalAmount, people.size, roundTo)
        return createSplitEntries(people, perPerson, vehicleType, startLocation, destination)
    }

    private fun calculateEqualSplit(
        totalAmount: Double,
        peopleCount: Int,
        decimalPlaces: Int
    ): Double {
        val rawAmount = totalAmount / peopleCount
        return "%.${decimalPlaces}f".format(rawAmount).toDouble()
    }

    private fun createSplitEntries(
        people: List<String>,
        amount: Double,
        vehicleType: VehicleType,
        start: String,
        destination: String
    ): List<SplitEntity> {
        return people.map { name ->
            SplitEntity(
                name = name,
                amount = amount,
                vehicleType = vehicleType.name,
                start = start,
                destination = destination,
                timestamp = System.currentTimeMillis()
            )
        }
    }
}
