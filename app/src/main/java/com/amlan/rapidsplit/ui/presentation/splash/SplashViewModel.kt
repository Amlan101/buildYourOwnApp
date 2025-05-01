package com.amlan.rapidsplit.ui.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashViewModel() : ViewModel() {
    val navigateToNext = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            delay(2500)
            navigateToNext.value = true
        }
    }
}