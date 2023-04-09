package com.example.fefu_fitnes_compose.Screens.Initialization.TopBars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainTopBar(text:String) {
    Box(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
    ){
       TopAppBar(
           modifier = Modifier.fillMaxSize(),
           backgroundColor = Color.Transparent,
           contentColor = Color.Black,
           elevation = 0.dp,
           content = {
               Row(
                   modifier = Modifier.fillMaxSize(),
                   horizontalArrangement = Arrangement.Center,
                   verticalAlignment = Alignment.CenterVertically
               ){
                   Text(
                       text = text,
                       fontSize = 20.sp,
                       fontWeight = FontWeight.Normal
                   )
               }
           }
       )
    }
}