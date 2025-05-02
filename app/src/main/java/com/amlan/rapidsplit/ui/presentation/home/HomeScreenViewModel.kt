package com.amlan.rapidsplit.ui.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlan.rapidsplit.domain.model.Destination
import com.amlan.rapidsplit.domain.model.RideHistory
import com.amlan.rapidsplit.domain.usecase.GetDestinationsUseCase
import com.amlan.rapidsplit.domain.usecase.GetRideHistoryUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDestinationsUseCase: GetDestinationsUseCase,
    private val getRideHistoryUseCase: GetRideHistoryUseCase
) : ViewModel() {

    // UI State
    var recentDestinations by mutableStateOf<List<Destination>>(emptyList())
        private set

    var popularDestinations by mutableStateOf<List<Destination>>(emptyList())
        private set

    var recentRides by mutableStateOf<List<RideHistory>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // User preferences and state
    var hasLocationPermission by mutableStateOf(false)
        private set

    var searchQuery by mutableStateOf("")
        private set

    init {
        loadInitialData()
    }

    fun loadInitialData() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                recentDestinations = getDestinationsUseCase.getRecentDestinations()
                popularDestinations = getDestinationsUseCase.getPopularDestinations()
                recentRides = getRideHistoryUseCase.getRecentRides()
            } catch (e: Exception) {
                errorMessage = e.message ?: "An unknown error occurred"
            } finally {
                isLoading = false
            }
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun setLocationPermission(granted: Boolean) {
        hasLocationPermission = granted
    }

    fun refreshData() {
        loadInitialData()
    }

    fun toggleFavorite(destinationId: String) {
        // Mocked up: Updating the UI state only
        val updatedRecentDestinations = recentDestinations.map { destination ->
            if (destination.id == destinationId) {
                destination.copy(isFavorite = !destination.isFavorite)
            } else {
                destination
            }
        }
        recentDestinations = updatedRecentDestinations

        val updatedPopularDestinations = popularDestinations.map { destination ->
            if (destination.id == destinationId) {
                destination.copy(isFavorite = !destination.isFavorite)
            } else {
                destination
            }
        }
        popularDestinations = updatedPopularDestinations
    }
}