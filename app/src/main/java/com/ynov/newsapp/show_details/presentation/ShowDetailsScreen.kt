package com.ynov.newsapp.show_details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ynov.newsapp.most_popular.presentation.MainViewModel

@Composable
fun ShowDetailsScreen(showId: String?, viewModel: MainViewModel) {
    // Fetch show details when the screen is shown
    LaunchedEffect(showId) {
        viewModel.fetchShowDetails(showId)
    }

    val showDetails by viewModel.showDetails.collectAsState()

    if (showDetails == null) {
        // Show loading state or error
        CircularProgressIndicator()
    } else {
        // Display show details in a Card
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = showDetails?.tvShow?.name ?: "", style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Genre: ${showDetails?.tvShow?.genres ?: "Unknown"}")
                Text(text = "Rating: ${showDetails?.tvShow?.rating ?: "N/A"}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Release Date: ${showDetails?.tvShow?.start_date ?: "N/A"}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Description: ${showDetails?.tvShow?.description ?: "No description available."}")
            }
        }
    }
}
