package com.cgs.dailypulse.composables

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTopAppBar(
    title: String
) {
    TopAppBar(
        title = { Text(title) },
        modifier = Modifier.statusBarsPadding()
    )
}