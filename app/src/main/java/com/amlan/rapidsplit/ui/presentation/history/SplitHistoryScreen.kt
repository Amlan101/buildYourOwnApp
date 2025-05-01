package com.amlan.rapidsplit.ui.presentation.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.navigation.Screen
import org.koin.androidx.compose.get
import java.text.DateFormat
import java.util.Date

@Composable
fun SplitHistoryScreen(
    navController: NavHostController,
    viewModel: SplitHistoryViewModel = get()
) {
    Column(Modifier.padding(16.dp)) {
        Text("Ride Split History", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(
                count = viewModel.splitList.size,
                key = { index -> viewModel.splitList[index].id }
            ) { index ->
                val item = viewModel.splitList[index]
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text("Name: ${item.name}")
                        Text("Amount: ₹${item.amount}")
                        Text("Route: ${item.start} → ${item.destination}")
                        Text("Vehicle: ${item.vehicleType}")
                        Text("Time: ${DateFormat.getDateTimeInstance().format(Date(item.timestamp))}")
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        Button(onClick = { navController.navigate(Screen.Home.route) }) {
            Text("Back to Home")
        }
    }
}
