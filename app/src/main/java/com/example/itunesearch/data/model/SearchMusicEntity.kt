package com.example.itunesearch.data.model

data class SearchMusicEntity(
    val resultCount: Int,
    val results: List<MusicEntity>
)