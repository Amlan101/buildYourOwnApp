package com.amlan.rapidsplit.domain.usecase
import com.amlan.rapidsplit.domain.model.RideHistory

// Added for Home Screen UI

class GetRideHistoryUseCase {
    fun getRecentRides(): List<RideHistory> {
        return listOf(
            RideHistory(
                id = "ride1",
                from = "Home",
                to = "KIIT University",
                date = "Today, 10:30 AM",
                fare = 75.0,
                rideType = "Bike"
            ),
            RideHistory(
                id = "ride2",
                from = "KIIT University",
                to = "Esplanade Mall",
                date = "Yesterday, 6:15 PM",
                fare = 120.0,
                rideType = "Auto"
            )
        )
    }
}
