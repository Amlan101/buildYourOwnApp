package com.amlan.rapidsplit.ui.presentation.fuelsplit

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.data.local.db.entity.SplitEntity
import com.amlan.rapidsplit.navigation.Screen
import org.koin.androidx.compose.get

@Composable
fun FuelSplitScreen(
    navController: NavHostController,
    viewModel: FuelSplitViewModel = get()
) {

    val ride = viewModel.ride

    BackHandler {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route) { inclusive = true }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Add back navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Go Back")
            }
            Text("Split Ride Fare", style = MaterialTheme.typography.titleLarge)
        }

        Spacer(Modifier.height(16.dp))

        // Show ride total
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Ride Details", style = MaterialTheme.typography.titleMedium)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text("From: ${ride?.startLocation ?: "--"}")
                Text("To: ${ride?.destination ?: "--"}")
                Text("Total Fare: ₹${ride?.estimatedPrice ?: "--"}")
            }
        }

        Spacer(Modifier.height(24.dp))

        // Add friends section
        Text("Add Friends to Split With:", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = viewModel.friendName,
                onValueChange = { viewModel.friendName = it },
                label = { Text("Friend's Name") },
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(8.dp))

            Button(onClick = { viewModel.addFriend() }) {
                Text("Add")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Show split summary
        if (viewModel.splitList.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Split Summary:", style = MaterialTheme.typography.titleMedium)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    viewModel.splitList.forEach { splitEntry: SplitEntity ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(splitEntry.name)
                            Text("₹${"%.2f".format(splitEntry.amount)}")
                        }
                    }
                }
            }
        } else if (viewModel.friends.isEmpty()) {
            Text("Add friends to split the fare with them",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(Modifier.weight(1f))

        // Finish button
        Button(
            onClick = {
                viewModel.saveSplits()
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Finish & Return Home")
        }
    }
}