package com.awesome.model

data class newsmodel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)