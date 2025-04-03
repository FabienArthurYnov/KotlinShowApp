package com.ynov.newsapp.everything.data.dto


import com.google.gson.annotations.SerializedName
import com.ynov.newsapp.everything.domain.model.EverythingResponse

data class EverythingResponseDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

fun EverythingResponseDto.toEverythingResponse(): EverythingResponse {
    return EverythingResponse(
        articles = articles.map { articleDto ->
            articleDto.toArticle()
        }
    )
}