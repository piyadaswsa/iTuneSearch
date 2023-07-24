package com.example.itunesearch.presentation.screen_music_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.itunesearch.R
import com.example.itunesearch.presentation.common_display_view.ErrorScreen
import com.example.itunesearch.presentation.common_display_view.LargeMusicCard
import com.example.itunesearch.presentation.common_display_view.LoadingBar
import com.example.itunesearch.presentation.common_display_view.PreviewMusicIcon
import com.example.itunesearch.presentation.common_display_view.PriceTag
import com.example.itunesearch.presentation.theme.CustomPurple

@Composable
fun TrackDetailScreen(
    navController: NavController,
    viewModel: MusicDetailViewModel = hiltViewModel(),
    state: MusicDetailState = viewModel.state.value
) {
    val scrollState = rememberScrollState()
    var toolbarHeight by remember { mutableStateOf(200.dp) }
    val customIcon = painterResource(R.drawable.ic_back_button)

    Column {
        if (state.isLoading) {
            LoadingBar()
        } else if (state.error.isNotEmpty()) {
            ErrorScreen()
        } else {
            Box {
                Icon(painter = customIcon,
                    contentDescription = "Back",
                    tint = Color(0xFFD9D9D9),
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .zIndex(1f)
                        .clickable {
                            navController.navigateUp()
                        })
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Image(
                        painter = rememberImagePainter(state.trackDetail?.artworkUrl100),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .background(Color.Gray),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                            .align(
                                alignment = Alignment.CenterHorizontally
                            )
                            .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.widthIn(max = 280.dp)
                        ) {
                            Text(
                                text = state.trackDetail?.trackName.orEmpty(),
                                style = MaterialTheme.typography.h1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = state.trackDetail?.artistName.orEmpty(),
                                style = MaterialTheme.typography.h2,
                                overflow = TextOverflow.Ellipsis,
                                color = CustomPurple
                            )
                            Text(
                                text = state.trackDetail?.collectionName.orEmpty(),
                                style = MaterialTheme.typography.h4,
                                overflow = TextOverflow.Ellipsis,
                                color = CustomPurple,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                            Text(
                                text = "Release : ${state.trackDetail?.releaseDate.orEmpty()}",
                                style = MaterialTheme.typography.h4,
                                overflow = TextOverflow.Ellipsis,
                                color = CustomPurple,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            PriceTag(price = state.trackDetail?.trackPrice ?: 0.0, 14.sp)
                            Text(
                                text = stringResource(id = R.string.album_of_song),
                                style = MaterialTheme.typography.h1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(top = 24.dp, bottom = 10.dp)
                            )
                            state.trackDetail?.let {
                                LargeMusicCard(
                                    music = it, isDisplayPrice = true, isDisplayAsAlbum = true
                                )
                            }
                            Box(modifier = Modifier.padding(vertical = 100.dp))
                        }
                        state.trackDetail?.let { PreviewMusicIcon(it.previewUrl) }
                    }
                }
            }
        }
    }

    LaunchedEffect(scrollState.value) {
        toolbarHeight = 240.dp - scrollState.value.toFloat().coerceIn(0f, 240f).dp
    }
}
