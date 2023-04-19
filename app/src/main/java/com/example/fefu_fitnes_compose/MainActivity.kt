package com.example.fefu_fitnes_compose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.Screens.BottomNavigate.MainScreen
import com.example.fefu_fitnes_compose.Screens.Initialization.InitializationNavigationMain
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.UI.RegistrationUI
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.InitializationUI
import com.example.fefu_fitnes_compose.Screens.Initialization.SplashScreen.SplashScreenUI
import com.example.fefu_fitnes_compose.Screens.ScreenElements.QrCard

class MainActivity : ComponentActivity() {

    @SuppressLint("RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            val userInitialization = RegisterRepository.userInit

            if(!userInitialization)
                InitializationNavigationMain()
            else
                MainScreen()
        }
    }
}
