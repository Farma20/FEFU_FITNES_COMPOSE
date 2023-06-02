package com.example.fefu_fitnes_compose.Screens.BottomNavigate

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.MainMenuNav
import com.example.fefu_fitnes_compose.Screens.ProfilePackage.ProfileUI
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.UI.QrScannerUI
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI.ServicesUI
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.UI.TimeTableUI
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavGraph(navController: NavHostController){
    AnimatedNavHost(
        navController = navController,
        startDestination = BottomBarScreen.Main.rout
    ){
        composable(
            route = BottomBarScreen.Main.rout,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ){
            MainMenuNav()
        }
        composable(
            route = BottomBarScreen.TimeTable.rout,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ){
            TimeTableUI()
        }

        composable(
            route = BottomBarScreen.QrScanner.rout,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ){
            Surface {
                QrScannerUI()
            }
        }

        composable(
            route = BottomBarScreen.Services.rout,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ){
            ServicesUI()
        }
        composable(
            route = BottomBarScreen.Profile.rout,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ){
            ProfileUI()
        }
    }
}