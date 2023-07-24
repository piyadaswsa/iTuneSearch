package com.example.itunesearch.presentation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object SearchScreen: Screen("search_screen")
    object MusicDetailScreen: Screen("music_detail_screen")
}