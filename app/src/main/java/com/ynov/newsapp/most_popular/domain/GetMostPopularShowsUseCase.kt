package com.ynov.newsapp.most_popular.domain

import com.ynov.newsapp.most_popular.data.ShowRepository

class GetMostPopularShowsUseCase(private val repository: ShowRepository) {
    suspend fun execute() = repository.getMostPopularShows()
}