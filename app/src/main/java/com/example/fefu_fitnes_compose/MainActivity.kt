package com.example.fefu_fitnes_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fefu_fitnes_compose.Screens.BottomNavigate.MainScreen
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.InitializationUI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitializationUI()
        }
    }
}
