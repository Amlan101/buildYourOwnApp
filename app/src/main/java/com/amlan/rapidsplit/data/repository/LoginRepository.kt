package com.amlan.rapidsplit.data.repository

interface LoginRepository {
    fun login(email: String): Boolean
}
