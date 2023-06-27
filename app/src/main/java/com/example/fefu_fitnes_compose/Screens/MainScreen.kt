package com.example.fefu_fitnes_compose.Screens.BottomNavigate

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.GreyDark
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navController = rememberAnimatedNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController)},
    ) {innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomNavGraph(navController = navController)
        }
    }
}


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Main,
        BottomBarScreen.TimeTable,
        BottomBarScreen.QrScanner,
        BottomBarScreen.Services,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentUser = MainRepository.userData.observeAsState(initial = UserDataModel())

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        screens.forEach { screen ->
            if (
                !(currentUser.value.type == "admin" || currentUser.value.type == "coach") &&
                screen.rout == "qrScannerScreen"
            ) {

            } else {
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }

        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        icon = {
          Icon(painterResource(id = screen.icon), contentDescription = "iconDescription")
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.rout
        } == true,
        selectedContentColor = BlueLight,
        unselectedContentColor = GreyDark,
        onClick = {
            navController.navigate(screen.rout)
        }
    )
}