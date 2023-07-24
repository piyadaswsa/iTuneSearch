package com.example.itunesearch.data.service

import com.example.itunesearch.data.model.SearchMusicEntity
import javax.inject.Inject

class SearchMusicApi @Inject constructor(
    private val service: SearchMusicService
) {

    suspend fun getTopChartMusic(term: String): SearchMusicEntity {
        return service.getTopChartMusic(term = term)
    }

    suspend fun getAllMusic(term: String): SearchMusicEntity {
        return service.getSearchMusic(term = term, limit = "100")
    }

    suspend fun getSearchMusic(keyword: String): SearchMusicEntity {
        return service.getSearchMusic(keyword)
    }

    suspend fun lookupTrackDetailById(id: Long): SearchMusicEntity {
        return service.lookupTrackDetailById(id)
    }

}