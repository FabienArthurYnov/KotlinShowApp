package com.ynov.newsapp.everything.data.service

import com.ynov.newsapp.everything.data.dto.EverythingResponseDto
import com.ynov.newsapp.everything.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface EverythingService {

    @GET(Constants.EVERYTHING)
    suspend fun getEverything(
        @Query("q") query: String = Constants.QUERY,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): EverythingResponseDto
}