package com.ynov.newsapp.everything.data.repository

import com.ynov.newsapp.everything.data.dto.toEverythingResponse
import com.ynov.newsapp.everything.data.service.EverythingService
import com.ynov.newsapp.everything.domain.model.EverythingResponse
import com.ynov.newsapp.everything.domain.repository.EverythingRepository
import javax.inject.Inject

class EverythingRepositoryImpl @Inject constructor(
    private val service: EverythingService
) : EverythingRepository {
    override suspend fun getEverything(): EverythingResponse {
        return service.getEverything().toEverythingResponse()
    }
}