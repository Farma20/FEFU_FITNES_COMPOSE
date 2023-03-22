package com.example.fefu_fitnes_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fefu_fitnes_compose.Screens.BottomNavigate.MainScreen
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.MainMenuUI
import com.example.fefu_fitnes_compose.ui.theme.FEFU_FITNES_COMPOSETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MainScreen()
        }
    }
}
