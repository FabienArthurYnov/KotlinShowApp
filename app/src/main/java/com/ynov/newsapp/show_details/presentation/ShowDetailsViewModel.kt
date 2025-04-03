package com.ynov.newsapp.show_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ynov.newsapp.most_popular.data.RetrofitInstance.api
import com.ynov.newsapp.show_details.data.ShowDetails
import com.ynov.newsapp.show_details.data.toShowDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShowDetailsViewModel : ViewModel() {
    private val _showDetails = MutableStateFlow<ShowDetails?>(null)
    val showDetails: StateFlow<ShowDetails?> = _showDetails

    fun fetchShowDetails(showId: Int) {
        viewModelScope.launch {
            try {
                val response = api.getShowDetails(showId.toString())
                if (response.isSuccessful) {
                    _showDetails.value = response.body()?.toShowDetails()
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
