package com.amlan.rapidsplit.domain.model
import com.amlan.rapidsplit.R

// Added for Home Screen UI

data class RideHistory(
    val id: String,
    val from: String,
    val to: String,
    val date: String,
    val fare: Double,
    val rideType: String,
    val splitWithFriends: Boolean = false,
    val splitAmount: Double? = null
)

data class RideOption(
    val title: String,
    val iconRes: Int
)

fun getRideOptions(): List<RideOption> {
    return listOf(
        RideOption("Parcel", R.drawable.ic_parcel),
        RideOption("Auto", R.drawable.ic_auto),
        RideOption("Cab Economy", R.drawable.ic_cab),
        RideOption("Bike", R.drawable.ic_bike)
    )
}
