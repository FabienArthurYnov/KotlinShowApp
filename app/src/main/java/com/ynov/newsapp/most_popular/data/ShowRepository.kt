package com.ynov.newsapp.most_popular.data

import android.util.Log
import com.ynov.newsapp.show_details.data.ShowDetailsResponse
import retrofit2.Response

data class ShowResponse(val tv_shows: List<Show>)
data class Show(val id: Int, val name: String, val image_thumbnail_path: String?)

class ShowRepository {
    suspend fun getMostPopularShows(): List<Show> {
        val response = RetrofitInstance.api.getMostPopularShows(1).tv_shows
        Log.d("ShowRepository", "Shows fetched: $response")  // Log response
        return response
    }

    // Method to get show details from the API
    suspend fun getShowDetails(showId: String?): Response<ShowDetailsResponse> {
        val response = RetrofitInstance.api.getShowDetails(showId)
        Log.d("API Response", "Response: ${response.body()}")

        return response
    }

}
