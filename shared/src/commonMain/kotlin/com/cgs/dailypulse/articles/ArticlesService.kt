package com.cgs.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(
    private val httpClient: HttpClient
) {
    private val country = "us"
    private val category = "business"
    private val apiKey = "4437061b5ce44a76ad1bfe52c8792595"

    suspend fun fetchArticles(): List<Article> {
        val response: ArticlesResponse = httpClient.get(
            "https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey"
        ).body()

        return response.articles
    }
}