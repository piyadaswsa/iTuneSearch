package com.example.itunesearch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.itunesearch.presentation.screen_home.HomeScreen
import com.example.itunesearch.presentation.screen_music_detail.TrackDetailScreen
import com.example.itunesearch.presentation.screen_search.SearchScreen
import com.example.itunesearch.presentation.theme.SearchMusicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchMusicTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController, startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController)
                        }
                        composable(route = Screen.SearchScreen.route) {
                            SearchScreen(navController)
                        }
                        composable(route = Screen.MusicDetailScreen.route + "/{trackId}") {
                            TrackDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

object NavigationKeys {
    object Arg {
        const val PARAM_TRACK_ID = "trackId"
    }
}
