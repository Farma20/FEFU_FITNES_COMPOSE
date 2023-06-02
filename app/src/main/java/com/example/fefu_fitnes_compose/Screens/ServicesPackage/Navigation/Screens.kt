package com.example.fefu_fitnes_compose.Screens.ServicesPackage.Navigation

sealed class Screens(val route:String) {
    object ServicesScreen: Screens("servicesScreen")
    object ServiceScreen: Screens("serviceScreen")
}