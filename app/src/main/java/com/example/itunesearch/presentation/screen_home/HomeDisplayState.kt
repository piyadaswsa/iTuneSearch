package com.example.itunesearch.presentation.screen_home

import com.example.itunesearch.domain.model.MusicDetail

data class HomeDisplayState(
    val isLoadingTopChart: Boolean = false,
    val isLoadingAlbum: Boolean = false,
    val isLoadingAll: Boolean = false,
    val musicList: List<MusicDetail> = emptyList(),
    val albumList: List<MusicDetail> = emptyList(),
    val allList: List<MusicDetail> = emptyList(),
    val error: String = ""
)