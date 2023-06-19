package com.example.fefu_fitnes_compose.Screens.ScreenElements

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyServicesQrCard() {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .height(107.dp),
        elevation = 3.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Абонементов нет",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}