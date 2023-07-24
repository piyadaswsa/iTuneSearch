package com.example.itunesearch.presentation.screen_music_detail

import com.example.itunesearch.domain.model.MusicDetail

data class MusicDetailState(
    val isLoading: Boolean = false, val trackDetail: MusicDetail? = null, val error: String = ""
)