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
    var phoneNumber by mutableStateOf("")
    var otp by mutableStateOf("")
    var isLoggedIn by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    var isOtpSent by mutableStateOf(false)
    var isOtpVerified by mutableStateOf(false)

    fun login() {
        isLoading = true
        errorMessage = null

        try {
            // Mock up: validating the OTP
            isLoggedIn = loginUseCase.login(email)
            isOtpVerified = true
        } catch (e: Exception) {
            errorMessage = e.message ?: "Login failed"
            isLoggedIn = false
        } finally {
            isLoading = false
        }
    }

    fun sendOtp(phone: String) {
        isLoading = true
        errorMessage = null

        try {
            // Mock up: calling API to send OTP
            phoneNumber = phone
            isOtpSent = true
        } catch (e: Exception) {
            errorMessage = e.message ?: "Failed to send OTP"
            isOtpSent = false
        } finally {
            isLoading = false
        }
    }

    fun verifyOtp(otpValue: String) {
        isLoading = true
        errorMessage = null

        try {
            // Mock up: verifying the OTP with backend
            otp = otpValue
            login()
        } catch (e: Exception) {
            errorMessage = e.message ?: "OTP verification failed"
            isOtpVerified = false
        } finally {
            isLoading = false
        }
    }

    fun resetLoginState() {
        email = ""
        phoneNumber = ""
        otp = ""
        isLoggedIn = false
        isOtpSent = false
        isOtpVerified = false
        errorMessage = null
    }
}