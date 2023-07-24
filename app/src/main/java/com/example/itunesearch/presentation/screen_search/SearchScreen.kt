package com.example.itunesearch.presentation.screen_search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.itunesearch.R
import com.example.itunesearch.presentation.Screen
import com.example.itunesearch.presentation.common_display_view.ErrorScreen
import com.example.itunesearch.presentation.common_display_view.LargeMusicCard
import com.example.itunesearch.presentation.common_display_view.LoadingBar
import com.example.itunesearch.presentation.common_display_view.SearchBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    state: SearchDisplayState = viewModel.state.value
) {
    val customIcon = painterResource(R.drawable.ic_back_button)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { },
                backgroundColor = Color.Transparent,
                contentColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = customIcon,
                            contentDescription = "Back",
                            tint = Color(0xFFD9D9D9)
                        )
                    }
                }
            )
        }
    ) {
        var searchText = remember { mutableStateOf("") }
        val coroutineScope = rememberCoroutineScope()
        val get: () -> Unit = {
            coroutineScope.launch {
                viewModel.getSearchMusic(keyword = searchText.value)
            }
        }

        Column(modifier = Modifier.padding(top = 10.dp, start = 30.dp, end = 30.dp)) {
            SearchBar { result ->
                searchText.value = result
                get()
            }

            if (state.isLoading) {
                LoadingBar()
            }  else if(state.hasSearch && state.searchList.isEmpty()) {
                ErrorScreen("Not found: ${searchText.value}")
            } else if(state.error.isNotEmpty()) {
                ErrorScreen()
            } else {
                Box {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 40.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                    ) {
                        items(state.searchList) { item ->
                            LargeMusicCard(
                                music = item,
                                isDisplayPrice = true,
                                onItemClick = { id ->
                                    navController.navigate(Screen.MusicDetailScreen.route + "/${id}")
                                }
                            )
                        }
                    }
                }
            }

        }
    }
}