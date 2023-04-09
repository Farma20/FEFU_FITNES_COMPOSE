package com.example.fefu_fitnes_compose.Screens.Initialization.Navigation

sealed class Screen(val route: String){
    object InitializationScreen: Screen("initializationScreen")
    object RegistrationScreen: Screen("registrationScreen")
    object SplashScreen: Screen("splashScreen")
}
