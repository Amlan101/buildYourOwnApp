package com.amlan.rapidsplit.domain.usecase

import com.amlan.rapidsplit.domain.model.SplitEntry

class CalculateSplitUseCase {
    fun execute(totalAmount: Double, people: List<String>): List<SplitEntry> {
        val perPerson = if (people.isNotEmpty()) totalAmount / people.size else 0.0
        return people.map { name -> SplitEntry(name, String.format("%.2f", perPerson).toDouble()) }
    }
}
