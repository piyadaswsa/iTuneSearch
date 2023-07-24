package com.example.itunesearch.domain.use_case

import com.example.itunesearch.data.service.SearchMusicApi
import com.example.itunesearch.data.model.toMusicDetail
import com.example.itunesearch.domain.model.MusicDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopChartMusicUseCase @Inject constructor(
    private val searchMusicApi: SearchMusicApi
) {

    suspend fun getTopChartMusic(): List<MusicDetail> = withContext(Dispatchers.IO) {
        return@withContext searchMusicApi.getTopChartMusic("ba").results.map { it.toMusicDetail() }
    }

    suspend fun getTopChartAlbum(): List<MusicDetail> = withContext(Dispatchers.IO) {
        return@withContext searchMusicApi.getTopChartMusic("to").results.map { it.toMusicDetail() }
    }

}