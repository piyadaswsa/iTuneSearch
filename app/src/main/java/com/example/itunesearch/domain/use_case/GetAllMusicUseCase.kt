package com.example.itunesearch.domain.use_case

import com.example.itunesearch.data.service.SearchMusicApi
import com.example.itunesearch.data.model.toMusicDetail
import com.example.itunesearch.domain.model.MusicDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllMusicUseCase @Inject constructor(
    private val searchMusicApi: SearchMusicApi
) {

    suspend fun getAllMusic(): List<MusicDetail> = withContext(
        Dispatchers.IO) {
        return@withContext searchMusicApi.getAllMusic("be").results.map { it.toMusicDetail() }
    }
}