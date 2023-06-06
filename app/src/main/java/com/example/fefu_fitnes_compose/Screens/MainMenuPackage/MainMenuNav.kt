package com.example.fefu_fitnes_compose.Screens.MainMenuPackage

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Navigation.Screen
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.Navigation.Screens
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI.ServiceUI
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI.ServicesUI
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainMenuNav() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Screen.MainMenuScreen.route){
        composable(
            route = Screen.MainMenuScreen.route,
            exitTransition = {
                slideOutHorizontally (
                    targetOffsetX = {-1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popEnterTransition = {
                slideInHorizontally (
                    initialOffsetX = {-1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
        ){
            MainMenuUI(navController = navController)

        }
        composable(
            route = Screen.AllBookingScreen.route,
            enterTransition = {
                slideInHorizontally (
                    initialOffsetX = {1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popExitTransition = {
                slideOutHorizontally (
                    targetOffsetX = {1080},
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
        ){
            AllBookingUI()

        }
    }
}


