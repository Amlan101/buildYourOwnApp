package com.amlan.rapidsplit.data.repository.impl

import com.amlan.rapidsplit.data.repository.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override fun login(email: String): Boolean {
        return email.contains("@")
    }
}
