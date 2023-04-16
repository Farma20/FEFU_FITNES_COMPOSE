package com.example.fefu_fitnes_compose.Screens.Initialization.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Models.User
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Repository.DataBaseRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.Initialization.Navigation.Screen
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import kotlinx.coroutines.delay

@Composable
fun SplashScreenUI(navController: NavController) {

    LaunchedEffect(key1 = true){
        delay(2500)
        DataBaseRepository.get().getAllUserData().observeForever{
            if (it.isEmpty() || it[0].userToken == ""){
                navController.navigate(Screen.InitializationScreen.route){
                    popUpTo(0)
                }
            }else{
                RegisterRepository.userToken = it[0].userToken
                RegisterRepository.qrToken = it[0].qrToken
                RegisterRepository.userType = it[0].userType
                RegisterRepository.userInit = true
            }
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(BlueDark, BlueLight)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(top = 200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_screen_fefu_label_one),
                contentDescription = null)
            Image(
                modifier = Modifier.padding(top = 8.dp),
                painter = painterResource(id = R.drawable.splash_screen_fefu_label_two),
                contentDescription = null)
        }
    }
}