package com.example.itunesearch.presentation.screen_search

import com.example.itunesearch.domain.model.MusicDetail

data class SearchDisplayState(
    val isLoading: Boolean = false,
    val hasSearch: Boolean = false,
    val searchList: List<MusicDetail> = emptyList(),
    val error: String = ""
)
