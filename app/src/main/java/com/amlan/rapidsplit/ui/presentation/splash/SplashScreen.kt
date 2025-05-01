package com.amlan.rapidsplit.ui.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.navigation.Screen
import org.koin.androidx.compose.get

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = get()
) {
    val navigate = viewModel.navigateToNext.collectAsState()

    if (navigate.value) {
        LaunchedEffect(Unit) {
            navController.navigate(Screen.Onboarding.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("RapidSplit", style = MaterialTheme.typography.headlineLarge)
    }
}
