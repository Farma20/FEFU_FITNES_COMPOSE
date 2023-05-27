package com.example.fefu_fitnes_compose.Screens.ScreenElements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.UI.SubmitUserDialog
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.ViewModel.QrViewModel
import com.example.fefu_fitnes_compose.Screens.ScreenElements.Animation.LoadingAnimation
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
        if(qrViewModel.qrUserDataShort.value == null){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.54f)
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                LoadingAnimation(
                    circleSize = 8.dp,
                    circleColor = BlueLight,
                    spaceBetween = 4.dp,
                    travelDistance = 6.dp
                )
            }
        }
        else{
            val openDialog = remember { mutableStateOf(false) }
            if (openDialog.value){
                if(qrViewModel.qrUserDataFool.value == null)
                    MainRepository.getQrUserDataFoolFromServer()
                SubmitUserDialog(openDialog, qrViewModel)
            }

            Text(
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp, bottom = 8.dp),
                text = "Пользователь",
                fontSize = 20.sp,
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .clickable{
                        openDialog.value = !openDialog.value
                    },
                elevation = 3.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(110.dp)
                            .padding(horizontal = 12.dp),
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
                                text = qrViewModel.qrUserDataShort.value!!.secondName,
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
                                text = qrViewModel.qrUserDataShort.value!!.firstName,
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
                                text = if(qrViewModel.qrUserDataShort.value!!.thirdName == null)
                                            "нет"
                                        else
                                            qrViewModel.qrUserDataShort.value!!.thirdName!!
                                ,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
            Text(
                modifier = Modifier
                    .padding(top = 5.dp, start = 8.dp, bottom = 8.dp),
                text = "Ближайшая запись",
                fontSize = 20.sp,
            )

            if (qrViewModel.qrUserNearEventData.value == null){
                QrEmptyCard()
            }
            else
                QrNearEventCard(event = qrViewModel.qrUserNearEventData.value!!)

            var buttonClicked by remember { mutableStateOf(false) }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                onClick = { buttonClicked = !buttonClicked },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = BlueLight,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Показать все занятия")
            }
            AnimatedVisibility(
                visible = buttonClicked,
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ){
                    if (qrViewModel.qrNextBooking.value!!.isEmpty() || qrViewModel.qrNextBooking.value!![0].eventId == null)
                        items(1){
                            QrEmptyCard()
                        }
                    else
                        items(qrViewModel.qrNextBooking.value!!.count()){
                            QrEventCard(event = qrViewModel.qrNextBooking.value!![it])
                        }
                    item { 
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}