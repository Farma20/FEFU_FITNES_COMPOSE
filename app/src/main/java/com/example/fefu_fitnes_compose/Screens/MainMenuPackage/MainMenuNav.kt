package com.example.fefu_fitnes_compose.Screens.MainMenuPackage

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Navigation.Screen
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
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.AllBookingScreen.route ->
                        slideInHorizontally(
                            initialOffsetX = { 300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screen.AllBookingScreen.route ->
                        slideOutHorizontally(
                            targetOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Screen.AllBookingScreen.route ->
                        slideInHorizontally(
                            initialOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    else -> null
                }
            }
        ){
            MainMenuUI(navController = navController)
        }
        composable(
            route = Screen.AllBookingScreen.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.MainMenuScreen.route ->
                        slideInHorizontally(
                            initialOffsetX = { 300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screen.MainMenuScreen.route ->
                        slideOutHorizontally(
                            targetOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    Screen.MainMenuScreen.route ->
                        slideOutHorizontally(
                            targetOffsetX = { 300 },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    else -> null
                }
            }
        ){
            AllBookingUI()
        }
    }
}
