package com.ynov.newsapp.most_popular.data

import com.ynov.newsapp.show_details.data.ShowDetailsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit API Interface
interface EpisodateApi {
    @GET("show-details")
    suspend fun getShowDetails(@Query("q") showId: String?): Response<ShowDetailsResponse>
    @GET("most-popular")
    suspend fun getMostPopularShows(@Query("page") page: Int): ShowResponse
}

// Singleton for Retrofit Instance
object RetrofitInstance {
    private const val BASE_URL = "https://www.episodate.com/api/"

    val api: EpisodateApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EpisodateApi::class.java)
    }
}

