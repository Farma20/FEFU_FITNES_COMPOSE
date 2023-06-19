package com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Navigation

sealed class TimeTableScreen(val route: String){
    object TimeTableView: TimeTableScreen("TimeTableView")
    object ServicesView: TimeTableScreen("ServicesView")
}