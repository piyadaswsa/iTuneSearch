package com.example.itunesearch.presentation.screen_home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesearch.domain.use_case.GetAllMusicUseCase
import com.example.itunesearch.domain.use_case.GetTopChartMusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopChartMusicUseCase: GetTopChartMusicUseCase,
    private val getAllMusicUseCase: GetAllMusicUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeDisplayState())
    val state: State<HomeDisplayState> = _state

    init {
        viewModelScope.launch {
            getSearchMusicTopChart()
        }
    }

    private suspend fun getSearchMusicTopChart() {
        viewModelScope.launch {
            _state.value = HomeDisplayState(
                isLoadingTopChart = true,
                isLoadingAlbum = true,
                isLoadingAll = true
            )
            try {
                val musicList = getTopChartMusicUseCase.getTopChartMusic()
                val albumList = getTopChartMusicUseCase.getTopChartAlbum()
                val allList = getAllMusicUseCase.getAllMusic()
                if (musicList.isNotEmpty() && albumList.isNotEmpty() && allList.isNotEmpty()) {
                    _state.value = HomeDisplayState(
                        musicList = musicList, albumList = albumList, allList = allList
                    )
                }
            } catch (e: Exception) {
                _state.value = HomeDisplayState(
                    isLoadingTopChart = false,
                    isLoadingAlbum = false,
                    isLoadingAll = false,
                    error = e.localizedMessage ?: "Loading Failed"
                )
            }
        }
    }
}