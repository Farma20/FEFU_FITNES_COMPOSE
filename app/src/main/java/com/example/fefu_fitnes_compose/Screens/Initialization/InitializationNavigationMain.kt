package com.example.fefu_fitnes_compose.Screens.Initialization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fefu_fitnes_compose.Screens.Initialization.Navigation.Screen
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.UI.RegistrationUI
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.InitializationUI

@Composable
fun InitializationNavigationMain(successInitialization: MutableState<Boolean>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.InitializationScreen.route){
        composable(route = Screen.InitializationScreen.route){
            InitializationUI(navController = navController, successInitialization)
        }
        composable(route = Screen.RegistrationScreen.route){
            RegistrationUI()
        }
    }
}