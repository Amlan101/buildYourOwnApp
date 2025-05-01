package com.amlan.rapidsplit.ui.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage

    fun updateCurrentPage(page: Int) {
        _currentPage.value = page
    }

    // Navigate to Login Screen
    fun onContinueClick(navController: NavHostController) {
        viewModelScope.launch {
            navController.navigate(Screen.Login.route) {
                // Remove onboarding from back stack so user can't go back
                popUpTo(Screen.Onboarding.route) { inclusive = true }
            }
        }
    }

    // Skip to the last onboarding page
    fun skipToLast(totalPages: Int) {
        _currentPage.value = totalPages - 1
    }
}