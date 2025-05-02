package com.amlan.rapidsplit.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.R
import com.amlan.rapidsplit.navigation.Screen
import com.amlan.rapidsplit.ui.theme.RapidoGreen
import com.amlan.rapidsplit.ui.theme.RapidoYellow
import org.koin.androidx.compose.get

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = get()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.loadInitialData()
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        if (viewModel.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = RapidoYellow
            )
        }

        viewModel.errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            SearchDestinationBar(
                onMenuClick = { /* TODO: Implement drawer or menu action */ },
                onSearchClick = { navController.navigate(Screen.RideSelection.route) }
            )

            if (viewModel.recentDestinations.isNotEmpty()) {
                Text(
                    "Recent Destinations",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                RecentDestinations(
                    destinations = viewModel.recentDestinations,
                    onItemClick = { destination ->
                        navController.navigate(Screen.RideSelection.route)
                    }
                )
            }

            RideOptions(
                onRideOptionClick = { navController.navigate(Screen.RideSelection.route) }
            )

            PromotionalBanner()

            if (viewModel.popularDestinations.isNotEmpty()) {
                Text(
                    "Go Places with Rapido",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                LazyRowPopularDestinations(
                    destinations = viewModel.popularDestinations,
                    onDestinationClick = { destination ->
                        navController.navigate(Screen.RideSelection.route)
                    }
                )
            }

            ParcelDeliveryBanner()

            BrandingSection()

            Spacer(modifier = Modifier.height(96.dp))
        }

        // Bottom Action Buttons
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { navController.navigate(Screen.RideSelection.route) },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RapidoYellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bike),
                        contentDescription = "Book a ride",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Book a Ride", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                Button(
                    onClick = { navController.navigate(Screen.FuelSplit.route) },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RapidoGreen,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                ) {
                    SplitFareIcon()
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Split Fare", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}

