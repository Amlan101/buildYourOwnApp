package com.amlan.rapidsplit.domain.usecase

import com.amlan.rapidsplit.data.repository.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    fun login(email: String): Boolean {
        return email.isNotEmpty()
    }
}
