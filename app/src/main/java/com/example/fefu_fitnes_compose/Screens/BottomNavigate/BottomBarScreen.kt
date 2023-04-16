package com.example.fefu_fitnes_compose.Screens.BottomNavigate

import com.example.fefu_fitnes_compose.R

sealed class BottomBarScreen(
    val rout: String,
    val title: String,
    val icon: Int,
){
    object Main: BottomBarScreen(
        rout = "mainScreen",
        title = "Главная",
        icon = R.drawable.bottom_navigate_main
    )

    object TimeTable: BottomBarScreen(
        rout = "timeTableScreen",
        title = "Расписание",
        icon = R.drawable.bottom_navigate_tables
    )

    object QrScanner: BottomBarScreen(
        rout = "qrScannerScreen",
        title = "QR сканер",
        icon = R.drawable.qr_scanner
    )

    object Services: BottomBarScreen(
        rout = "servicesScreen",
        title = "Абонименты",
        icon = R.drawable.bottom_navigate_paid
    )

    object Profile: BottomBarScreen(
        rout = "profileScreen",
        title = "Профиль",
        icon = R.drawable.bottom_navigate_profile
    )
}