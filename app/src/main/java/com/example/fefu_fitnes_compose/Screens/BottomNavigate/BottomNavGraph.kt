package com.example.fefu_fitnes_compose.Screens.BottomNavigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.MainMenuUI
import com.example.fefu_fitnes_compose.Screens.ProfilePackage.ProfileUI
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.ServicesUI
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.UI.TimeTableUI

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = BottomBarScreen.TimeTable.rout){
        composable(route = BottomBarScreen.Main.rout){
            MainMenuUI()
        }
        composable(route = BottomBarScreen.TimeTable.rout){
            TimeTableUI()
        }
        composable(route = BottomBarScreen.Services.rout){
            ServicesUI()
        }
        composable(route = BottomBarScreen.Profile.rout){
            ProfileUI()
        }
    }
}