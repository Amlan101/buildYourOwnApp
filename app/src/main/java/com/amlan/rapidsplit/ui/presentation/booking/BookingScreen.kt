package com.amlan.rapidsplit.ui.presentation.booking


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.navigation.Screen
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel
import org.koin.androidx.compose.get


@Composable
fun BookingScreen(
    navController: NavHostController,
    sharedViewModel: RideSharedViewModel = get()
) {
    // Add back handler
    BackHandler {
        // Clear the ride when going back
        sharedViewModel.setConfirmedRide(null)
        navController.popBackStack()
    }

    // Observe the ride state
    val ride by remember { sharedViewModel.confirmedRide }

    ride?.let { confirmedRide ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Add a back button at the top
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    sharedViewModel.setConfirmedRide(null)
                    navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go Back")
                }
                Text("Booking Details", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Start: ${confirmedRide.startLocation}")
            Text("Destination: ${confirmedRide.destination}")
            Text("Vehicle: ${confirmedRide.vehicleType}")
            Text("Price: ₹${confirmedRide.estimatedPrice}")
            Text("Time: ${confirmedRide.estimatedTime} mins")
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate(Screen.Payment.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Proceed to Payment")
            }
        }
    } ?: run {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No ride selected.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Go Back to Select a Ride")
            }
        }
    }
}