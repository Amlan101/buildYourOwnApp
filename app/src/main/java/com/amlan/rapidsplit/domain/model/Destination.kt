package com.amlan.rapidsplit.domain.model
import com.amlan.rapidsplit.R

// Added for Home Screen UI

data class Destination(
    val id: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean = false
)

data class RecentDestination(
    val name: String,
    val address: String
)

data class PopularDestination(
    val title: String, val imageRes: Int
)

fun getPopularDestinations(): List<PopularDestination> {
    return listOf(
        PopularDestination("Biju Patnaik International Airport", R.drawable.airport_image),
        PopularDestination("Bhubaneswar Railway Station", R.drawable.railway_station_image),
        PopularDestination("Jaydev Vihar", R.drawable.landmark_image)
    )
}

