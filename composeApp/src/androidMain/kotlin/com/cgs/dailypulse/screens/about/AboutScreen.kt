package com.cgs.dailypulse.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cgs.dailypulse.AboutData
import com.cgs.dailypulse.composables.AppTopAppBar

@Composable
fun AboutScreen(
) {
    AboutView()
}

@Composable
private fun AboutView(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        items(AboutData.informations()) { item ->
            RowView(item.title, item.subtitle)
        }
    }
}

@Composable
private fun RowView(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body2
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.body1
        )
    }
}