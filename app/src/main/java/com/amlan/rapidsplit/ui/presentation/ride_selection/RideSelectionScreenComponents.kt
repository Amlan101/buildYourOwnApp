package com.amlan.rapidsplit.ui.presentation.ride_selection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amlan.rapidsplit.R
import com.amlan.rapidsplit.domain.model.Ride
import com.amlan.rapidsplit.domain.model.VehicleType
import com.amlan.rapidsplit.ui.theme.RapidoBackgroundGrey
import com.amlan.rapidsplit.ui.theme.RapidoBlack
import com.amlan.rapidsplit.ui.theme.RapidoGrey
import com.amlan.rapidsplit.ui.theme.RapidoLightGrey
import com.amlan.rapidsplit.ui.theme.RapidoYellow

@Composable
fun EstimateDetailsCard(
    ride: Ride,
    onConfirmRide: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "RIDE DETAILS",
                fontWeight = FontWeight.Bold,
                color = RapidoBlack,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Driver Rating and Vehicle Details
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(RapidoBackgroundGrey)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = "Driver",
                        tint = RapidoGrey,
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    // Rating row
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = RapidoYellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "4.8",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Vehicle info
                    Text(
                        text = "${ride.vehicleType.name.capitalize()} • 2 min away",
                        color = RapidoGrey,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Vehicle icon
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = RapidoBackgroundGrey,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    when (ride.vehicleType) {
                        VehicleType.BIKE -> Icon(
                            painter = painterResource(id = R.drawable.ic_bike),
                            contentDescription = "Bike",
                            tint = RapidoBlack,
                            modifier = Modifier.size(24.dp)
                        )
                        VehicleType.AUTO -> Icon(
                            painter = painterResource(id = R.drawable.ic_auto),
                            contentDescription = "Auto",
                            tint = RapidoBlack,
                            modifier = Modifier.size(24.dp)
                        )
                        VehicleType.CAR -> Icon(
                            painter = painterResource(id = R.drawable.ic_cab),
                            contentDescription = "Car",
                            tint = RapidoBlack,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = RapidoLightGrey.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(16.dp))

            // Fare and Time Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Price column
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "ESTIMATED FARE",
                        fontSize = 12.sp,
                        color = RapidoGrey
                    )
                    Text(
                        text = "₹${ride.estimatedPrice}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = RapidoBlack
                    )
                }

                // Divider
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp)
                        .background(RapidoLightGrey.copy(alpha = 0.5f))
                )

                // Time column
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "ESTIMATED TIME",
                        fontSize = 12.sp,
                        color = RapidoGrey
                    )
                    Text(
                        text = "${ride.estimatedTime} mins",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = RapidoBlack
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Confirm Button
            Button(
                onClick = onConfirmRide,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RapidoYellow),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("CONFIRM RIDE", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
fun VehicleOption(
    type: VehicleType,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) RapidoYellow else Color.Transparent
    val backgroundColor = if (isSelected) RapidoYellow.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface

    Card(
        modifier = modifier
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            // Vehicle Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                when (type) {
                    VehicleType.BIKE -> Icon(
                        painter = painterResource(id = R.drawable.ic_bike),
                        contentDescription = "Bike",
                        tint = if (isSelected) RapidoYellow else RapidoGrey,
                        modifier = Modifier.size(28.dp)
                    )
                    VehicleType.AUTO -> Icon(
                        painter = painterResource(id = R.drawable.ic_auto),
                        contentDescription = "Auto",
                        tint = if (isSelected) RapidoYellow else RapidoGrey,
                        modifier = Modifier.size(28.dp)
                    )
                    VehicleType.CAR -> Icon(
                        painter = painterResource(id = R.drawable.ic_cab),
                        contentDescription = "Car",
                        tint = if (isSelected) RapidoYellow else RapidoGrey,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = type.name.capitalize(),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = if (isSelected) RapidoBlack else RapidoGrey
            )
        }
    }
}
