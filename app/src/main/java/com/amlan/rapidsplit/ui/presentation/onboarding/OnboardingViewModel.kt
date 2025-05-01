package com.amlan.rapidsplit.ui.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.navigation.Screen

class OnboardingViewModel(): ViewModel() {
    fun onContinueClick(navController: NavHostController) {
        navController.navigate(Screen.Login.route)
    }
}