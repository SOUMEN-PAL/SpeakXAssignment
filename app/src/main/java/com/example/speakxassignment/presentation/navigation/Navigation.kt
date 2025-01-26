package com.example.speakxassignment.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.speakxassignment.presentation.HomeScreen
import com.example.speakxassignment.presentation.searchScreen.SearchScreen
import com.example.speakxassignment.presentation.viewmodels.ItemViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    viewModel: ItemViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.homeScreen.route
    ) {
        composable(route = Screens.homeScreen.route){
            HomeScreen(viewModel = viewModel , navController = navController)
        }

        composable(route = Screens.searchScreen.route){
            SearchScreen(navController = navController , viewModel = viewModel)
        }
    }
}