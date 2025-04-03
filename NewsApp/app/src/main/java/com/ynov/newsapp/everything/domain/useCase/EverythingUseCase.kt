package com.ynov.newsapp.everything.domain.useCase

import com.ynov.newsapp.everything.domain.model.Article
import com.ynov.newsapp.everything.domain.repository.EverythingRepository
import com.ynov.newsapp.everything.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EverythingUseCase @Inject constructor(
    private val repository: EverythingRepository
) {
    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getEverything()
            emit(Resource.Success(response.articles))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }
}