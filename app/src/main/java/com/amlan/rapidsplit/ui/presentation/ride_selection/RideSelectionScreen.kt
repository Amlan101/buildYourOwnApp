package com.amlan.rapidsplit.ui.presentation.ride_selection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.R
import com.amlan.rapidsplit.domain.model.Ride
import com.amlan.rapidsplit.domain.model.VehicleType
import com.amlan.rapidsplit.navigation.Screen
import com.amlan.rapidsplit.ui.presentation.ride_selection.RideSelectionViewModel
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel
import com.amlan.rapidsplit.ui.theme.RapidoBackgroundGrey
import com.amlan.rapidsplit.ui.theme.RapidoBlack
import com.amlan.rapidsplit.ui.theme.RapidoGreen
import com.amlan.rapidsplit.ui.theme.RapidoGrey
import com.amlan.rapidsplit.ui.theme.RapidoLightGrey
import com.amlan.rapidsplit.ui.theme.RapidoRed
import com.amlan.rapidsplit.ui.theme.RapidoYellow
import kotlinx.coroutines.delay
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideSelectionScreen(
    navController: NavHostController,
    viewModel: RideSelectionViewModel = get(),
    sharedViewModel: RideSharedViewModel = get()
) {
    // Get the current confirmed ride state
    val confirmedRide by remember { sharedViewModel.confirmedRide }

    LaunchedEffect(confirmedRide) {
        if (confirmedRide != null) {
            delay(100)
            navController.navigate(Screen.Booking.route)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Your Ride", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Location Input Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Start Location Row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Start Location",
                            tint = RapidoGreen,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        OutlinedTextField(
                            value = viewModel.startLocation,
                            onValueChange = { viewModel.startLocation = it },
                            placeholder = { Text("Enter pickup location") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RapidoYellow,
                                unfocusedBorderColor = RapidoLightGrey
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = RapidoLightGrey.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(8.dp))

                    // Destination Row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Destination",
                            tint = RapidoRed,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        OutlinedTextField(
                            value = viewModel.destination,
                            onValueChange = { viewModel.destination = it },
                            placeholder = { Text("Enter destination") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RapidoYellow,
                                unfocusedBorderColor = RapidoLightGrey
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Vehicle Selection Section
            Text(
                text = "SELECT VEHICLE TYPE",
                fontWeight = FontWeight.Bold,
                color = RapidoGrey,
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Vehicle Selection Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                VehicleOption(
                    type = VehicleType.BIKE,
                    isSelected = viewModel.selectedVehicle == VehicleType.BIKE,
                    onClick = { viewModel.selectedVehicle = VehicleType.BIKE },
                    modifier = Modifier.weight(1f)
                )

                VehicleOption(
                    type = VehicleType.AUTO,
                    isSelected = viewModel.selectedVehicle == VehicleType.AUTO,
                    onClick = { viewModel.selectedVehicle = VehicleType.AUTO },
                    modifier = Modifier.weight(1f)
                )

                VehicleOption(
                    type = VehicleType.CAR,
                    isSelected = viewModel.selectedVehicle == VehicleType.CAR,
                    onClick = { viewModel.selectedVehicle = VehicleType.CAR },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Calculate Estimate Button
            Button(
                onClick = { viewModel.calculateEstimate() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RapidoYellow),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("ESTIMATE FARE", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            // Estimate Details Card
            AnimatedVisibility(
                visible = viewModel.rideEstimate != null,
                enter = fadeIn() + slideInVertically { it },
                exit = fadeOut() + slideOutVertically { it }
            ) {
                viewModel.rideEstimate?.let { ride ->
                    EstimateDetailsCard(
                        ride = ride,
                        onConfirmRide = { viewModel.confirmRide(sharedViewModel) }
                    )
                }
            }
        }
    }
}

private fun String.capitalize(): String {
    return this.lowercase().replaceFirstChar { it.uppercase() }
}