package com.example.itunesearch.presentation.common_display_view

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.itunesearch.R
import com.example.itunesearch.presentation.theme.CustomPurple
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer


@Composable
fun PreviewMusicIcon(musicUrl: String) {
    val context = LocalContext.current
    val player = remember { SimpleExoPlayer.Builder(context).build() }
    val isPlaying = remember { mutableStateOf(false) }

    val customPauseIcon = painterResource(R.drawable.ic_pause_circle_filled)
    val customPlayIcon = painterResource(R.drawable.ic_play_circle_filled)

    val mediaItem = MediaItem.fromUri(Uri.parse(musicUrl))
    player.setMediaItem(mediaItem)
    val eventListener = object : Player.EventListener {
        override fun onPlaybackStateChanged(state: Int) {
            if (state == Player.STATE_ENDED) {
                isPlaying.value = !isPlaying.value
                player.stop()
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            isPlaying.value = !isPlaying.value
            Toast.makeText(context, "Error loading media source", Toast.LENGTH_SHORT).show()
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            // do nothing
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            player.removeListener(eventListener)
            player.stop()
            player.release()
        }
    }

    Icon(painter = if (isPlaying.value) customPauseIcon else customPlayIcon,
        contentDescription = "Play Music",
        tint = CustomPurple,
        modifier = Modifier
            .size(72.dp)
            .clickable {
                isPlaying.value = !isPlaying.value
                if (isPlaying.value) {
                    player.addListener(eventListener)
                    player.prepare()
                    player.playWhenReady = true
                } else {
                    player.removeListener(eventListener)
                    player.stop()
                }
            })
}