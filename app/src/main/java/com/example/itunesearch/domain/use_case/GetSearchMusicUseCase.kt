package com.example.itunesearch.domain.use_case

import com.example.itunesearch.data.service.SearchMusicApi
import com.example.itunesearch.data.model.toMusicDetail
import com.example.itunesearch.domain.model.MusicDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSearchMusicUseCase @Inject constructor(
    private val searchMusicApi: SearchMusicApi
) {

    suspend fun getSearchMusicByKeyword(keyword: String): List<MusicDetail> = withContext(Dispatchers.IO) {
        return@withContext searchMusicApi.getSearchMusic(keyword).results.map { it.toMusicDetail() }
    }

    suspend fun getTrackDetailById(id: Long): List<MusicDetail> = withContext(Dispatchers.IO) {
        return@withContext searchMusicApi.lookupTrackDetailById(id).results.map { it.toMusicDetail() }
    }

}