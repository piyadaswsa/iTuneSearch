package com.example.itunesearch.data.service

import com.example.itunesearch.data.model.SearchMusicEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMusicService {

    @GET("search")
    suspend fun getTopChartMusic(
        @Query("term") term: String = "barbie",
        @Query("media") media: String = "music",
        @Query("limit") limit: String = "10"
    ): SearchMusicEntity

    @GET("search")
    suspend fun getSearchMusic(
        @Query("term") term: String,
        @Query("media") media: String = "music",
        @Query("limit") limit: String = "15"
    ): SearchMusicEntity

    @GET("lookup")
    suspend fun lookupTrackDetailById(
        @Query("id") trackId: Long
    ): SearchMusicEntity
}