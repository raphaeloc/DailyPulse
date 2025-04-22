package com.cgs.dailypulse.articles

import com.cgs.dailypulse.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel : BaseViewModel() {
    private val _state: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState.Loading)
    private val service: ArticlesService

    val state: StateFlow<ArticlesState> get() = _state

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        service = ArticlesService(httpClient)
    }

    fun getArticles() {
        scope.launch {
            val fetchedArticles = service.fetchArticles()
            _state.emit(ArticlesState.Success(fetchedArticles))
        }
    }
}