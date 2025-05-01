package com.amlan.rapidsplit.ui.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.amlan.rapidsplit.domain.usecase.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    var email by mutableStateOf("")
    var isLoggedIn by mutableStateOf(false)

    fun login() {
        isLoggedIn = loginUseCase.login(email)
    }
}
