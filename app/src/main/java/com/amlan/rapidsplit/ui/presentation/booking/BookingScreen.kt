package com.amlan.rapidsplit.ui.presentation.booking

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.domain.model.Ride
import com.amlan.rapidsplit.navigation.Screen
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel
import org.koin.androidx.compose.get

@Composable
fun BookingScreen(
    navController: NavHostController,
    sharedViewModel: RideSharedViewModel = get()
) {
    BackHandler {
        sharedViewModel.setConfirmedRide(null)
        navController.popBackStack()
    }

    // Observe the ride state
    val ride by remember { sharedViewModel.confirmedRide }

    ride?.let { confirmedRide ->
        BookingContentScreen(
            confirmedRide = confirmedRide,
            onBackClick = {
                sharedViewModel.setConfirmedRide(null)
                navController.popBackStack()
            },
            onProceedClick = {
                navController.navigate(Screen.Payment.route)
            }
        )
    } ?: run {
        NoRideSelectedScreen(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingContentScreen(
    confirmedRide: Ride,
    onBackClick: () -> Unit,
    onProceedClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            title = {
                Text(
                    "Booking Details",
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        )

        // Main Content
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Booking Status Card
            BookingStatusCard(
                status = "Booking Confirmed",
                bookingId = "RAPIDO${(100000..999999).random()}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ride Details Card
            RideDetailsCard(confirmedRide)

            Spacer(modifier = Modifier.height(16.dp))

            // Captain Card
            CaptainDetailsCard()

            Spacer(modifier = Modifier.weight(1f))

            // Payment Button
            Button(
                onClick = onProceedClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Proceed to Payment",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
