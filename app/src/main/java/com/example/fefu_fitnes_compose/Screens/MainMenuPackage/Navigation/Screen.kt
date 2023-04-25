package com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Navigation

sealed class Screen(val route: String){
    object MainMenuScreen: Screen("mainMenuScreen")
    object AllBookingScreen: Screen("allBookingScreen")
}
