package com.ynov.newsapp.show_details.domain

import com.ynov.newsapp.most_popular.data.ShowRepository
import com.ynov.newsapp.show_details.data.ShowDetailsResponse
import retrofit2.Response

class GetShowDetailsUseCase(private val showRepository: ShowRepository) {
    suspend fun execute(showId: String?): Response<ShowDetailsResponse> {
        return showRepository.getShowDetails(showId)
    }
}
