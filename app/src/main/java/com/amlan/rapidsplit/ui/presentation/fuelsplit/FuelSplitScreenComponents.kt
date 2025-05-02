package com.amlan.rapidsplit.ui.presentation.fuelsplit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.data.local.db.entity.SplitEntity
import com.amlan.rapidsplit.navigation.Screen
import com.amlan.rapidsplit.ui.theme.RapidoBlue
import com.amlan.rapidsplit.ui.theme.RapidoGreen
import com.amlan.rapidsplit.ui.theme.RapidoLightGrey
import com.amlan.rapidsplit.ui.theme.RapidoRed
import com.amlan.rapidsplit.ui.theme.RapidoYellow
import kotlin.collections.forEach


@Composable
fun HeaderSection(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(RapidoYellow.copy(alpha = 0.2f))
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Go Back",
                tint = RapidoYellow
            )
        }

        Text(
            "Split Ride Fare",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(48.dp))
    }
}

@Composable
fun RideDetailsCard(startLocation: String?, destination: String?, estimatedPrice: Double?) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.DirectionsCar,
                    contentDescription = "Ride",
                    tint = RapidoYellow,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Ride Details",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Divider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = RapidoLightGrey.copy(alpha = 0.5f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Start Location",
                    tint = RapidoGreen,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        "From",
                        style = MaterialTheme.typography.bodySmall,
                        color = RapidoLightGrey
                    )
                    Text(
                        startLocation ?: "--",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Destination",
                    tint = RapidoRed,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        "To",
                        style = MaterialTheme.typography.bodySmall,
                        color = RapidoLightGrey
                    )
                    Text(
                        destination ?: "--",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Divider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = RapidoLightGrey.copy(alpha = 0.5f)
            )

            // Total fare
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Total Fare",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    "₹${estimatedPrice ?: "--"}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun FriendsSection(
    viewModel: FuelSplitViewModel,
    onAddFriendClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Friends Sharing the Ride",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (viewModel.friends.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = RapidoLightGrey.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "No Friends Added Yet",
                        style = MaterialTheme.typography.bodyMedium,
                        color = RapidoLightGrey
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = onAddFriendClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RapidoYellow,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add Friend"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Add a Friend")
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                items(viewModel.friends) { friend ->
                    FriendItem(friend) {
                        // Remove friend functionality to be added here
                    }
                }
            }
        }
    }
}

@Composable
fun FriendItem(name: String, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = RapidoYellow.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(RapidoYellow),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name.first().toString().uppercase(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            IconButton(
                onClick = onRemove,
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(RapidoLightGrey.copy(alpha = 0.2f))
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Remove Friend",
                    tint = RapidoLightGrey,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun SplitSummarySection(viewModel: FuelSplitViewModel) {
    AnimatedVisibility(
        visible = viewModel.splitList.isNotEmpty(),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Split Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Divider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = RapidoLightGrey.copy(alpha = 0.5f)
                )

                viewModel.splitList.forEach { splitEntry: SplitEntity ->
                    SplitEntryItem(splitEntry)
                }

                viewModel.ride?.let { ride ->
                    Divider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = RapidoLightGrey.copy(alpha = 0.5f)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Total",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "₹${ride.estimatedPrice}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = RapidoYellow
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SplitEntryItem(splitEntry: SplitEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(RapidoBlue.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = splitEntry.name.first().toString().uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = RapidoBlue
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                splitEntry.name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            "₹${"%.2f".format(splitEntry.amount)}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun FinishButton(navController: NavHostController, viewModel: FuelSplitViewModel) {
    Button(
        onClick = {
            viewModel.saveSplits()
            navController.navigate(Screen.PaymentSuccess.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = RapidoYellow,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            "Complete Payment",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}