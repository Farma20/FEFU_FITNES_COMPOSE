package com.example.fefu_fitnes_compose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.Screens.BottomNavigate.MainScreen
import com.example.fefu_fitnes_compose.Screens.Initialization.InitializationNavigationMain
import com.example.fefu_fitnes_compose.Screens.ScreenElements.QrCard

class MainActivity : ComponentActivity() {

    @SuppressLint("RememberReturnType", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            val userInitialization = RegisterRepository.userInit


            if(!userInitialization){
                InitializationNavigationMain()
            }
            else{
                MainScreen()
            }


//                QrCard()
//            ServiceUI()
        }
    }
}
