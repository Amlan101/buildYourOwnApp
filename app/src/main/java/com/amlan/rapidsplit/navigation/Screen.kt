package com.amlan.rapidsplit.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Home : Screen("home")
    object RideSelection : Screen("ride_selection")
    object Booking : Screen("booking")
    object Payment : Screen("payment")
    object FuelSplit : Screen("fuelsplit")
    object PaymentSuccess : Screen("payment_success")
}