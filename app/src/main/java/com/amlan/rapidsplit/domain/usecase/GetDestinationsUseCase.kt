package com.amlan.rapidsplit.domain.usecase
import com.amlan.rapidsplit.domain.model.Destination

// Added for Home Screen UI

class GetDestinationsUseCase {
    fun getRecentDestinations(): List<Destination> {
        return listOf(
            Destination(
                id = "1",
                name = "KIMS Hospital",
                address = "KIIT Road, Chandaka Industrial Estate, Patia",
                latitude = 20.3561,
                longitude = 85.8245
            ),
            Destination(
                id = "2",
                name = "Master Canteen",
                address = "Master Canteen Chowk, Kharvela Nagar, Bhubaneshwar",
                latitude = 20.2713,
                longitude = 85.8430
            )
        )
    }

    fun getPopularDestinations(): List<Destination> {
        return listOf(
            Destination(
                id = "3",
                name = "Biju Patnaik International Airport",
                address = "Airport Road, Bhubaneswar",
                latitude = 20.2462,
                longitude = 85.8177
            ),
            Destination(
                id = "4",
                name = "Bhubaneswar Railway Station",
                address = "Station Square, Master Canteen Area",
                latitude = 20.2713,
                longitude = 85.8430
            ),
            Destination(
                id = "5",
                name = "Jaydev Vihar",
                address = "Jaydev Vihar Square, Nandankanan Road",
                latitude = 20.2992,
                longitude = 85.8216
            )
        )
    }
}