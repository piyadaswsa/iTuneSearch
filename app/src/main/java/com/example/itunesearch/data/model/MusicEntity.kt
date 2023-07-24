package com.example.itunesearch.data.model

import com.example.itunesearch.common.Constants.DISPLAY_DATE_FORMAT
import com.example.itunesearch.common.Constants.RESPONSE_DATE_FORMAT
import com.example.itunesearch.domain.model.MusicDetail
import java.text.SimpleDateFormat
import java.util.Locale

data class MusicEntity(
    val artistId: Long,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val collectionArtistId: Int,
    val collectionArtistName: String,
    val collectionArtistViewUrl: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    val collectionId: Long,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionViewUrl: String,
    val country: String,
    val currency: String,
    val discCount: Int,
    val discNumber: Int,
    val isStreamable: Boolean,
    val kind: String,
    val previewUrl: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCensoredName: String,
    val trackCount: Int,
    val trackExplicitness: String,
    val trackId: Long,
    val trackName: String,
    val trackNumber: Int,
    val trackPrice: Double,
    val trackTimeMillis: Long,
    val trackViewUrl: String,
    val wrapperType: String
)

fun transformDate(dateString: String): String {
    val inputFormat = SimpleDateFormat(RESPONSE_DATE_FORMAT, Locale.getDefault())
    val outputFormat = SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.getDefault())
    val date = inputFormat.parse(dateString)
    return date?.let { outputFormat.format(date) } ?: run { "" }
}

fun MusicEntity.toMusicDetail(): MusicDetail {
    return MusicDetail(
        artistId = artistId,
        trackId = trackId,
        artistName = artistName,
        collectionName = collectionName,
        trackName = trackName,
        previewUrl = previewUrl,
        artworkUrl30 = artworkUrl30,
        artworkUrl60 = artworkUrl60,
        artworkUrl100 = artworkUrl100,
        collectionPrice = collectionPrice,
        trackPrice = trackPrice,
        releaseDate = transformDate(releaseDate),
        country = country,
        currency = currency,
        primaryGenreName = primaryGenreName
    )
}

