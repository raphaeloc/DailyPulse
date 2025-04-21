package com.cgs.dailypulse.articles

sealed class ArticlesState {
    object Loading : ArticlesState()

    data class Success(val articles: List<Article>) : ArticlesState()

    data class Error(val message: String) : ArticlesState()
}

