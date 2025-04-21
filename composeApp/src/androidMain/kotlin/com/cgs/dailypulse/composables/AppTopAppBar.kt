package com.cgs.dailypulse.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTopAppBar(
    title: String,
    actions: (@Composable RowScope.() -> Unit)? = null,
    navigationIcon:  @Composable() (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(title) },
        modifier = Modifier.statusBarsPadding(),
        actions =  {
            actions?.invoke(this)
        },
        navigationIcon = navigationIcon
    )
}