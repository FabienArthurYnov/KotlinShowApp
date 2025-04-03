package com.ynov.newsapp.show_details.domain

import ShowListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ynov.newsapp.most_popular.presentation.MainViewModel
import com.ynov.newsapp.show_details.presentation.ShowDetailsScreen

@Composable
fun AppNavHost(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // Pass the navController to ShowListScreen
            ShowListScreen(navController = navController, viewModel = viewModel)
        }
        composable("show_details/{showId}") { backStackEntry ->
            val showId = backStackEntry.arguments?.getString("showId")
            ShowDetailsScreen(viewModel = viewModel, showId = showId)
        }
    }
}
