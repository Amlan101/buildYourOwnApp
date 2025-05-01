package com.amlan.rapidsplit.ui.presentation.payment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.amlan.rapidsplit.domain.model.PaymentMethod
import com.amlan.rapidsplit.domain.usecase.SelectPaymentMethodUseCase

class PaymentViewModel(
    private val selectPaymentMethodUseCase: SelectPaymentMethodUseCase
): ViewModel() {

    var selectedMethod by mutableStateOf(PaymentMethod.UPI)
    var isPaymentConfirmed by mutableStateOf(false)

    fun confirmPayment() {
        isPaymentConfirmed = selectPaymentMethodUseCase.execute(selectedMethod)
    }
}