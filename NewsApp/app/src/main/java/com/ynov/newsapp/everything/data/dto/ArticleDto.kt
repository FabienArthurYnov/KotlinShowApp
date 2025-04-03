package com.ynov.newsapp.everything.data.dto


import com.google.gson.annotations.SerializedName
import com.ynov.newsapp.everything.domain.model.Article

data class ArticleDto(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: SourceDto,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)

fun ArticleDto.toArticle(): Article {
    return Article(
        content = "$publishedAt : $content",
        title = "$author - $title",
        urlToImage = urlToImage
    )
}