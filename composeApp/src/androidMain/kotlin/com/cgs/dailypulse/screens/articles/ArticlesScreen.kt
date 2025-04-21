package com.cgs.dailypulse.screens.articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cgs.dailypulse.articles.Article
import com.cgs.dailypulse.articles.ArticlesState
import com.cgs.dailypulse.articles.ArticlesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cgs.dailypulse.composables.AppTopAppBar

@Composable
fun ArticlesScreen(
    viewModel: ArticlesViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getArticles()
    }
    ArticlesView(viewModel)
}

@Composable
fun ArticlesView(
    viewModel: ArticlesViewModel
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            AppTopAppBar(
                "Articles"
            )
        }
    ) { innerPadding ->
        when (val currentState = state) {
            is ArticlesState.Loading -> LoadingView(
                Modifier.padding(innerPadding)
            )
            is ArticlesState.Success -> ContentView(
                currentState.articles,
                Modifier.padding(innerPadding)
            )
            is ArticlesState.Error -> ErrorView(
                currentState.message,
                Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun LoadingView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colors.primarySurface
        )
    }
}

@Composable
fun ErrorView(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}

@Composable
private fun ContentView(
    articles: List<Article>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(articles) { article ->
            ArticleItemView(article)
        }
    }
}

@Composable
private fun ArticleItemView(
    article: Article
) {
    Column(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = "Article image"
        )
        Text(text = article.title, style = MaterialTheme.typography.h6)
        Text(text = article.articleDescription)
        Text(
            text = article.date,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2.copy(color = Color.Gray)
        )
    }
}