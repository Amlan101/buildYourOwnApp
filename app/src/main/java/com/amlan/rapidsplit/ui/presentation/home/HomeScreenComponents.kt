package com.amlan.rapidsplit.ui.presentation.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.amlan.rapidsplit.R
import com.amlan.rapidsplit.domain.model.Destination
import com.amlan.rapidsplit.domain.model.getRideOptions
import com.amlan.rapidsplit.ui.theme.RapidoBackgroundGrey
import com.amlan.rapidsplit.ui.theme.RapidoGrey
import com.amlan.rapidsplit.ui.theme.RapidoYellow


@Composable
fun RecentDestinationItem(
    destination: Destination,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = destination.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = destination.address,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(onClick = { /* Favorite Logic */ }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Add to favorites",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Divider(
            modifier = Modifier.padding(start = 68.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Composable
fun LazyRowPopularDestinations(
    destinations: List<Destination>,
    onDestinationClick: (Destination) -> Unit
) {
    androidx.compose.foundation.lazy.LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(destinations.size) { index ->
            val destination = destinations[index]
            PopularDestinationItem(
                title = destination.name,
                imageRes = R.drawable.landmark_image,
                onClick = { onDestinationClick(destination) }
            )
        }
    }
}

@Composable
fun SearchDestinationBar(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Surface(
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = RapidoGrey
                )
            }

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clickable(onClick = onSearchClick),
                shape = RoundedCornerShape(24.dp),
                color = RapidoBackgroundGrey
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = RapidoGrey
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Where are you going?",
                        color = RapidoGrey,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun RideOptions(onRideOptionClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Explore",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View All",
                style = MaterialTheme.typography.bodyMedium,
                color = RapidoGrey,
                modifier = Modifier.clickable { onRideOptionClick() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        androidx.compose.foundation.lazy.LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(getRideOptions().size) { index ->
                val option = getRideOptions()[index]
                RideOptionItem(
                    title = option.title,
                    iconRes = option.iconRes,
                    onClick = onRideOptionClick
                )
            }
        }
    }
}

@Composable
fun RideOptionItem(
    title: String,
    iconRes: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(RapidoBackgroundGrey),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun PromotionalBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(120.dp)
            .clickable { /* Handle banner click */ },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Bike, Auto, Car,\nNever Too Far.",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tap to Know More",
                    style = MaterialTheme.typography.bodyMedium,
                    color = RapidoGrey
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp)
                    .background(RapidoYellow),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.banner_image),
                    contentDescription = "Promotional Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun PopularDestinationItem(
    title: String,
    imageRes: Int,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clickable(onClick = onClick)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ParcelDeliveryBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(100.dp)
            .clickable { /* Handle banner click */ },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Send items within the city.",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "First Parcel order for FREE\nUse Code: SAVE100",
                    style = MaterialTheme.typography.bodyMedium,
                    color = RapidoGrey
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Try Rapido Parcel",
                    style = MaterialTheme.typography.bodyMedium,
                    color = RapidoYellow.copy(alpha = 0.8f),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .background(
                            color = RapidoYellow.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .background(RapidoYellow),
                contentAlignment = Alignment.Center
            ) {
                ParcelDeliveryIcon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun BrandingSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "#goRapidSplit",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.ExtraBold,
            color = RapidoGrey.copy(alpha = 0.2f)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            IndianFlagIcon(modifier = Modifier.size(20.dp))

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Made for India",
                style = MaterialTheme.typography.bodyMedium,
                color = RapidoGrey
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            HeartIcon(
                modifier = Modifier.size(20.dp),
                tint = Color.Red
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Crafted by Amlan",
                style = MaterialTheme.typography.bodyMedium,
                color = RapidoGrey
            )
        }
    }
}

@Composable
fun HeartIcon(modifier: Modifier = Modifier, tint: Color = Color.Red) {
    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "Heart",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun IndianFlagIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1.5f)
            .background(Color.White, RoundedCornerShape(2.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFFFF9933))
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_wheel),
                        contentDescription = "Ashoka Chakra",
                        tint = Color(0xFF000080),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFF138808))
            )
        }
    }
}

@Composable
fun ParcelDeliveryIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_parcel),
            contentDescription = "Parcel Delivery",
            tint = Color.Black,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun SplitFareIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "÷",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_bike),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun RecentDestinations(
    destinations: List<Destination>,
    onItemClick: (Destination) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        destinations.forEach { destination ->
            RecentDestinationItem(
                destination = destination,
                onClick = { onItemClick(destination) }
            )
        }
    }
}
