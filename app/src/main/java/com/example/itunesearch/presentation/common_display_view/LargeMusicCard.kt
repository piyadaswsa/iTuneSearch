package com.example.itunesearch.presentation.common_display_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.itunesearch.domain.model.MusicDetail
import com.example.itunesearch.presentation.theme.CustomPurple

@Composable
fun LargeMusicCard(
    music: MusicDetail,
    isDisplayPrice: Boolean = false,
    isDisplayAsAlbum: Boolean = false,
    onItemClick: (Long) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .padding(vertical = 5.dp)
            .wrapContentHeight()
            .clickable { onItemClick(music.trackId) }
    ) {
        Image(
            painter = rememberImagePainter(data = music.artworkUrl100, builder = {
                size(OriginalSize)
            }),
            contentDescription = music.trackName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 160.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(
            modifier = Modifier
                .height(80.dp)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = if (isDisplayAsAlbum) music.collectionName else music.trackName,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 10.dp, bottom = 5.dp),
                style = TextStyle(
                    fontSize = 14.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = music.artistName,
                color = CustomPurple,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = 10.dp),
                style = TextStyle(
                    fontSize = 12.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (isDisplayPrice) {
            Box(modifier = Modifier.padding(start = 10.dp)) {
                PriceTag(price = if (isDisplayAsAlbum) music.collectionPrice else music.trackPrice)
            }
        }
    }
}