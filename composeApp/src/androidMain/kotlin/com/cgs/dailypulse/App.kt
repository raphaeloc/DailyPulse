package com.cgs.dailypulse

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.cgs.dailypulse.composables.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}