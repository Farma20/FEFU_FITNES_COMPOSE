package com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI.Elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun SuccessDialog(openDialog: MutableState<Boolean>) {
    Dialog(onDismissRequest = {openDialog.value = false}) {
        Surface(
            modifier = Modifier,
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = "Абонемент забронирован",
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier.padding(top=11.dp),
                    text = "Абонемент будет активирован сразу  после оплаты через спортивного администратора. ",
                    fontSize = 14.sp,
                    color = Color(0xFF383838)
                )
                Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp, end = 25.dp, bottom = 10.dp), contentAlignment = Alignment.CenterEnd){
//                    IconButton(onClick = { openDialog.value = false }) {
//
//                    }
                    Text(
                        modifier = Modifier.clickable { openDialog.value = false },
                        text = "ОК",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}