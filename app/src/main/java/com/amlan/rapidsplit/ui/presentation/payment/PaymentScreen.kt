package com.amlan.rapidsplit.ui.presentation.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.domain.model.PaymentMethod
import com.amlan.rapidsplit.navigation.Screen
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel
import org.koin.androidx.compose.get

@Composable
fun PaymentScreen(
    navController: NavHostController,
    viewModel: PaymentViewModel = get(),
    sharedViewModel: RideSharedViewModel = get()
) {
    val ride by remember { sharedViewModel.confirmedRide }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Add a back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go Back")
            }
            Text("Payment", style = MaterialTheme.typography.displayMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Safely access ride details with null check
        ride?.let { confirmedRide ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Ride Summary",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Start: ${confirmedRide.startLocation}")
                    Text("Destination: ${confirmedRide.destination}")
                    Text("Price: ₹${confirmedRide.estimatedPrice}")
                    Text("Time: ${confirmedRide.estimatedTime} mins")
                }
            }
        } ?: run {
            // Handle the case when ride is null
            Text("No ride selected. Please go back and select a ride first.")
            Button(onClick = { navController.popBackStack() }) {
                Text("Go Back")
            }
            return@Column
        }

        Spacer(Modifier.height(20.dp))
        Text("Select Payment Method:", style = MaterialTheme.typography.titleMedium)

        // Payment method selection
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                PaymentMethod.entries.forEach { method ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = viewModel.selectedMethod == method,
                            onClick = { viewModel.selectedMethod = method }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(method.name)
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("How do you want to proceed?", style = MaterialTheme.typography.titleMedium)

        // Payment buttons with better layout
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.confirmPayment()
                    if (viewModel.isPaymentConfirmed) {
                        navController.navigate(Screen.FuelSplit.route)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Split Fare")
            }

            Spacer(Modifier.width(16.dp))

            Button(
                onClick = {
                    viewModel.confirmPayment()
                    if (viewModel.isPaymentConfirmed) {
                        navController.navigate(Screen.PaymentSuccess.route)
                    }
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Pay Solo")
            }
        }
    }
}



