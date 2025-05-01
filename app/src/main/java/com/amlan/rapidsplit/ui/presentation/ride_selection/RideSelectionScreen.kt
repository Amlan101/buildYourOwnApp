package com.amlan.rapidsplit.ui.presentation.ride_selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.domain.model.VehicleType
import com.amlan.rapidsplit.navigation.Screen
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.get

@Composable
fun RideSelectionScreen(
    navController: NavHostController,
    viewModel: RideSelectionViewModel = get(),
    sharedViewModel: RideSharedViewModel = get()
) {
    // Get the current confirmed ride state
    val confirmedRide by remember { sharedViewModel.confirmedRide }

    // Navigation effect
    LaunchedEffect(confirmedRide) {
        if (confirmedRide != null) {
            // Add a slight delay to ensure state propagation
            delay(100)
            navController.navigate(Screen.Booking.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = viewModel.startLocation,
            onValueChange = { viewModel.startLocation = it },
            label = { Text("Start Location") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.destination,
            onValueChange = { viewModel.destination = it },
            label = { Text("Destination") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Text("Select Vehicle")
        Row {
            VehicleType.entries.forEach { type ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = viewModel.selectedVehicle == type,
                        onClick = {
                            viewModel.selectedVehicle = type
                        }
                    )
                    Text(type.name)
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.calculateEstimate() }) {
            Text("Estimate")
        }

        viewModel.rideEstimate?.let { ride ->
            Spacer(Modifier.height(16.dp))
            Text("Estimated Price: ₹${ride.estimatedPrice}")
            Text("Estimated Time: ${ride.estimatedTime} mins")
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                viewModel.confirmRide(sharedViewModel)
            }) {
                Text("Confirm Ride")
            }
        }
    }
}

