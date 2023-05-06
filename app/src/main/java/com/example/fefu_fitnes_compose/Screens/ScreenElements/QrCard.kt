package com.example.fefu_fitnes_compose.Screens.ScreenElements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.ViewModel.QrViewModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight

@Preview(showBackground = true)
@Composable
fun QrCard(qrViewModel: QrViewModel = viewModel()) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
         Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
             Box(modifier = Modifier
                 .width(50.dp)
                 .height(3.dp)
                 .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                 .background(Color.Gray))
         }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .padding(horizontal = 16.dp),
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "userImg"
            )
            Column() {
                Row() {
                    Text(
                        text = "Фамилия: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if(qrViewModel.qrUserData.secondName == null)"Нет" else qrViewModel.qrUserData.secondName!!,
                        fontSize = 16.sp
                    )
                }
                Row() {
                    Text(
                        text = "Имя: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if(qrViewModel.qrUserData.firstName == null)"Нет" else qrViewModel.qrUserData.firstName!!,
                        fontSize = 16.sp
                    )
                }
                Row() {
                    Text(
                        text = "Отчество: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Нет",
                        fontSize = 16.sp
                    )
                }
            }
        }
        Text(
            modifier = Modifier
                .padding(top = 5.dp, start = 8.dp, bottom = 8.dp),
            text = "Ближайшая запись",
            fontSize = 20.sp,
        )

         if (qrViewModel.qrUserNearEventData.eventId == null){
             QrEmptyCard()
         }
        else
            QrNearEventCard(event = qrViewModel.qrUserNearEventData)
        Spacer(modifier = Modifier.height(16.dp))

        var buttonClicked by remember { mutableStateOf(false) }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            onClick = { buttonClicked = !buttonClicked }
        ) {
            Text(text = "Показать все занятия")
        }
        AnimatedVisibility(
            visible = buttonClicked,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ){
                if (qrViewModel.qrNextBooking.isEmpty() || qrViewModel.qrNextBooking[0].eventId == null)
                    items(1){
                        EmptyCard()
                    }
                else
                    items(qrViewModel.qrNextBooking.count()){
                        QrEventCard(event = qrViewModel.qrNextBooking[it])
                    }
            }
        }
    }
}