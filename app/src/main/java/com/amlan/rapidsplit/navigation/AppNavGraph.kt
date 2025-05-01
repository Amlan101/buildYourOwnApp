package com.amlan.rapidsplit.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amlan.rapidsplit.ui.presentation.booking.BookingScreen
import com.amlan.rapidsplit.ui.presentation.fuelsplit.FuelSplitScreen
import com.amlan.rapidsplit.ui.presentation.home.HomeScreen
import com.amlan.rapidsplit.ui.presentation.login.LoginScreen
import com.amlan.rapidsplit.ui.presentation.onboarding.OnboardingScreen
import com.amlan.rapidsplit.ui.presentation.payment.PaymentScreen
import com.amlan.rapidsplit.ui.presentation.payment.PaymentSuccessScreen
import com.amlan.rapidsplit.ui.presentation.ride_selection.RideSelectionScreen
import com.amlan.rapidsplit.ui.presentation.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Onboarding.route) { OnboardingScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.RideSelection.route) { RideSelectionScreen(navController) }
        composable(Screen.Booking.route) { BookingScreen(navController) }
        composable(Screen.Payment.route) { PaymentScreen(navController) }
        composable(Screen.FuelSplit.route) { FuelSplitScreen(navController) }
        composable(Screen.PaymentSuccess.route) { PaymentSuccessScreen(navController) }
    }
}
