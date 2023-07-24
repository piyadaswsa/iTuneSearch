package com.example.itunesearch.domain.model

data class MusicDetail(
    val artistId: Long,
    val trackId: Long,
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val previewUrl: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val trackPrice: Double,
    val releaseDate: String,
    val country: String,
    val currency: String,
    val primaryGenreName: String
)

