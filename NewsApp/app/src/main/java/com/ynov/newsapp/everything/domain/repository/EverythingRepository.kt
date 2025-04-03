package com.ynov.newsapp.everything.domain.repository

import com.ynov.newsapp.everything.domain.model.EverythingResponse

interface EverythingRepository {
    suspend fun getEverything(): EverythingResponse
}