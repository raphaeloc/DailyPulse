package com.cgs.dailypulse

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.cgs.dailypulse.screens.articles.ArticlesScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        ArticlesScreen()
    }
}