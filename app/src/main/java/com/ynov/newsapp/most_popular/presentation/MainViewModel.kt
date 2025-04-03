package com.ynov.newsapp.most_popular.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ynov.newsapp.most_popular.data.Show
import com.ynov.newsapp.most_popular.domain.GetMostPopularShowsUseCase
import com.ynov.newsapp.show_details.data.ShowDetailsResponse
import com.ynov.newsapp.show_details.domain.GetShowDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getShowsUseCase: GetMostPopularShowsUseCase,
    private val getShowDetailsUseCase: GetShowDetailsUseCase // UseCase to fetch details
) : ViewModel() {

    private val _shows = MutableStateFlow<List<Show>>(emptyList())
    val shows: StateFlow<List<Show>> get() = _shows

    // StateFlow to hold the details of a single show
    private val _showDetails = MutableStateFlow<ShowDetailsResponse?>(null)
    val showDetails: StateFlow<ShowDetailsResponse?> get() = _showDetails

    init {
        viewModelScope.launch {
            try {
                _shows.value = getShowsUseCase.execute()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching shows: ${e.message}")
                // TODO Handle the error gracefully, e.g., show a fallback UI
            }
        }
    }

    // Function to fetch the details of a specific show
    fun fetchShowDetails(showId: String?) {
        viewModelScope.launch {
            try {
                val response = getShowDetailsUseCase.execute(showId)
                if (response.isSuccessful) {
                    _showDetails.value = response.body()  // Store the response in _showDetails
                    Log.d("debug", "Fetched data successfully")
                } else {
                    Log.e("MainViewModel", "Error fetching show details: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching show details: ${e.message}")
                _showDetails.value = null  // Handle error gracefully
            }
        }
    }
}
