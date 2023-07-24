package com.example.itunesearch.presentation.common_display_view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.itunesearch.domain.model.MusicDetail

@Composable
fun ScrollableRow(
    items: List<MusicDetail>,
    content: @Composable (MusicDetail) -> Unit,
    modifier: Modifier = Modifier,
    onItemClick: (Long) -> Unit = {}
) {
    LazyRow(modifier = modifier) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .widthIn(min = 225.dp),
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            ) {
                Surface(
                    color = Color.Transparent, modifier = Modifier.padding(0.dp)
                ) {
                    content(item)
                }
            }
        }
    }
}