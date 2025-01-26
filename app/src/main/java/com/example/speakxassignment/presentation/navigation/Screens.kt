package com.example.speakxassignment.presentation.navigation

sealed class Screens(val route : String) {
    data object homeScreen : Screens("homeScreen")
    data object searchScreen : Screens("searchScreen")
}