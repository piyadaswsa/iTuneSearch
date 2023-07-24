package com.example.itunesearch.presentation.screen_music_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesearch.domain.use_case.GetSearchMusicUseCase
import com.example.itunesearch.presentation.NavigationKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicDetailViewModel @Inject constructor (
    private val getSearchMusicUseCase: GetSearchMusicUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MusicDetailState())
    val state: State<MusicDetailState> = _state

    init {
        savedStateHandle.get<String>(NavigationKeys.Arg.PARAM_TRACK_ID)?.let { id ->
            getTrackDetailById(id.toLong())
        }
    }

    private fun getTrackDetailById(id: Long) {
        viewModelScope.launch {
            _state.value = MusicDetailState(isLoading = true)
            try {
                val musicList = getSearchMusicUseCase.getTrackDetailById(id)
                _state.value = MusicDetailState(trackDetail = musicList[0])
            } catch (e: Exception) {
                _state.value = MusicDetailState(
                    isLoading = false,
                    error = e.localizedMessage ?: "Loading Failed"
                )
            }
        }
    }
}