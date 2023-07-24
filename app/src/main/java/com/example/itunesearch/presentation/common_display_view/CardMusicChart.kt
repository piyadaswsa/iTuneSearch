package com.example.itunesearch.presentation.common_display_view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.itunesearch.domain.model.MusicDetail
import com.example.itunesearch.presentation.theme.TagTextColor
import com.example.itunesearch.presentation.theme.CustomPurple

@Composable
fun CardMusicChart(music: MusicDetail, onItemClick: (Long) -> Unit = {}) {
    Row(modifier = Modifier
        .animateContentSize()
        .clickable { onItemClick(music.trackId) }
        .width(240.dp)
        .height(70.dp)) {
        Box(
            modifier = Modifier
                .weight(2.5f)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Image(painter = rememberImagePainter(data = music.artworkUrl100, builder = {
                size(OriginalSize)
            }),
                contentDescription = music.trackName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 2.dp, top = 2.dp, bottom = 2.dp)
                    .clip(RoundedCornerShape(8.dp)))
        }
        Column(
            modifier = Modifier
                .weight(5f)
                .padding(start = 10.dp, end = 6.dp)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Text(
                text = music.trackName,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 10.dp, bottom = 5.dp),
                style = TextStyle(
                    fontSize = 14.sp
                ),
                maxLines = 1,
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
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .border(
                        width = 1.dp, color = CustomPurple, shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 6.dp),
            ) {
                Text(
                    text = "${music.trackPrice} $",
                    color = TagTextColor,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 10.sp
                    ),
                )
            }
        }
    }
}