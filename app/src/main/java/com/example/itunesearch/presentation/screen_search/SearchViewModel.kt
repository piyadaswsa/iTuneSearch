package com.example.itunesearch.presentation.screen_search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesearch.domain.use_case.GetSearchMusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor (
    private val getSearchMusicUseCase: GetSearchMusicUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SearchDisplayState())
    val state: State<SearchDisplayState> = _state

    init {
        _state.value = SearchDisplayState(hasSearch = false)
    }

    suspend fun getSearchMusic(keyword: String) {
        viewModelScope.launch {
            _state.value = SearchDisplayState(isLoading = true)
            try {
                val musicList = getSearchMusicUseCase.getSearchMusicByKeyword(
                    keyword.replace(" ", "+")
                )
                _state.value = SearchDisplayState(searchList = musicList, hasSearch = true)
            } catch (e: Exception) {
                _state.value = SearchDisplayState(
                    isLoading = false,
                    hasSearch = true,
                    error = e.localizedMessage ?: "Loading Failed"
                )
            }
        }
    }
}