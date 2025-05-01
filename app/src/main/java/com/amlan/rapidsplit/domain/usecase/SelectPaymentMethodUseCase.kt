package com.amlan.rapidsplit.domain.usecase

import com.amlan.rapidsplit.domain.model.PaymentMethod

class SelectPaymentMethodUseCase {
    fun execute(method: PaymentMethod): Boolean {
        // Mocked: always returns true
        return true
    }
}
