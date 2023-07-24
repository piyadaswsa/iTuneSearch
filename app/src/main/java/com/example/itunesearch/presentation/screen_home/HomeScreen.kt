package com.example.itunesearch.presentation.screen_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.itunesearch.R
import com.example.itunesearch.presentation.Screen
import com.example.itunesearch.presentation.common_display_view.CardMusicChart
import com.example.itunesearch.presentation.common_display_view.ErrorScreen
import com.example.itunesearch.presentation.common_display_view.LargeMusicCard
import com.example.itunesearch.presentation.common_display_view.LoadingBar
import com.example.itunesearch.presentation.common_display_view.ScrollableGrid
import com.example.itunesearch.presentation.common_display_view.ScrollableRow
import com.example.itunesearch.presentation.common_display_view.SearchBar
import com.example.itunesearch.presentation.theme.CustomPurple

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    state: HomeDisplayState = viewModel.state.value
) {

    Scaffold {
        LazyColumn(modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            content = {
                if (state.isLoadingAlbum || state.isLoadingTopChart || state.isLoadingAll) {
                    item { LoadingBar() }
                } else if (state.error.isNotEmpty()) {
                    item { ErrorScreen() }
                } else {
                    item {
                        Text(
                            text = stringResource(id = R.string.welcome_text),
                            textAlign = TextAlign.Left,
                            style = MaterialTheme.typography.h1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }

                    item {
                        SearchBar(navController)
                    }

                    item {
                        Text(
                            text = stringResource(id = R.string.top_chart_text),
                            textAlign = TextAlign.Left,
                            style = MaterialTheme.typography.h1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                        )
                    }

                    item {
                        ScrollableRow(
                            items = state.musicList,
                            content = { music ->
                                CardMusicChart(music = music, onItemClick = { id ->
                                    navController.navigate(Screen.MusicDetailScreen.route + "/${id}")
                                })
                            },
                        )
                        Box(modifier = Modifier.padding(8.dp))
                    }

                    item {
                        var tabIndex by remember { mutableStateOf(0) }
                        val tabTitles = listOf("All", "Alternative", "Pop", "Country", "Rock")
                        MusicChartSection(tabTitles = tabTitles,
                            selectedTabIndex = tabIndex,
                            onTabSelected = { position ->
                                tabIndex = position
                            }) { position ->
                            val displayItems = if (position == 0) {
                                state.allList
                            } else {
                                state.allList.filter { musicDetail ->
                                    musicDetail.primaryGenreName.equals(tabTitles[tabIndex], true)
                                }
                            }
                            ScrollableGrid(items = displayItems, content = { music ->
                                LargeMusicCard(
                                    music = music,
                                    isDisplayPrice = true,
                                    onItemClick = { id ->
                                        navController.navigate(Screen.MusicDetailScreen.route + "/${id}")
                                    })
                            })
                        }
                    }
                }
            })
    }
}

@Composable
fun MusicChartSection(
    tabTitles: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Int) -> Unit
) {
    Surface(
        modifier = modifier, color = Color.Transparent
    ) {
        Column {
            var size by remember { mutableStateOf(Size.Zero) }
            ScrollableTabRow(modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    size = coordinates.size.toSize()
                },
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp,
                backgroundColor = Color.Transparent,
                contentColor = CustomPurple,
                divider = {},
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .wrapContentSize()
                            .size(width = 8.dp, height = 8.dp)
                            .background(color = Color.White, shape = CircleShape)
                    )
                }) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(selected = selectedTabIndex == index,
                        onClick = { onTabSelected(index) },
                        text = {
                            Text(
                                title, style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = if (selectedTabIndex == index) FontWeight(500) else FontWeight(
                                        400
                                    ),
                                )
                            )
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = CustomPurple,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(bottom = 10.dp)
                    )
                }
            }
            content(selectedTabIndex)
        }
    }
}


