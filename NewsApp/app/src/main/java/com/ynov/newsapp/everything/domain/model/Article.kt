package com.ynov.newsapp.everything.domain.model

data class Article(
    val content: String,
    val title: String,
    val urlToImage: String?
)