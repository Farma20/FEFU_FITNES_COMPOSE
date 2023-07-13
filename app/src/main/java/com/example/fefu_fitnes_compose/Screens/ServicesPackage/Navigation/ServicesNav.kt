package com.example.fefu_fitnes_compose.Screens.ServicesPackage.Navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI.ServiceUI
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI.ServicesUI
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.ViewModel.ServicesViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ServicesNav(servicesViewModel: ServicesViewModel = viewModel()) {

    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Screens.ServicesScreen.route){
        composable(
            route =  Screens.ServicesScreen.route,
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
            ServicesUI(navController = navController, servicesViewModel)
        }
        composable(
            route =  Screens.ServiceScreen.route,
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
            ServiceUI(servicesViewModel)
        }
    }
}
