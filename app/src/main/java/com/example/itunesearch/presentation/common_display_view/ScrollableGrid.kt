package com.example.itunesearch.presentation.common_display_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.itunesearch.domain.model.MusicDetail

@Composable
fun ScrollableGrid(
    items: List<MusicDetail>,
    content: @Composable (MusicDetail) -> Unit
) {
    Column {
        items.chunked(2).map { rowItems ->
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 10.dp, horizontal = 5.dp)
            ) {
                items(rowItems) { item ->
                    content(item)
                }
            }
        }
    }
}
