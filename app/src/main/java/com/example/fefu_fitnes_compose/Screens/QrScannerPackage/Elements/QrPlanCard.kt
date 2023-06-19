package com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.QrUserPlansItem
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.ViewModel.QrViewModel
import com.example.fefu_fitnes_compose.Screens.ScreenElements.Animation.LoadingAnimation
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.serviceCardColorOne
import com.example.fefu_fitnes_compose.ui.theme.serviceCardTextColor

@Composable
fun QrPlanCard(modifier: Modifier = Modifier,planData:QrUserPlansItem, qrViewModel: QrViewModel) {

    val click by remember{ mutableStateOf(false) }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = serviceCardColorOne
        )
    ) {

        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                modifier= Modifier.height(40.dp),
                text = planData.serviceName,
                color = serviceCardTextColor,
                fontSize = 18.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "Колличество занятий: ${planData.planTypeCapacity}",
                        color = serviceCardTextColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                    Text(
                        text = "Статус: ${
                            when(planData.planStatus){
                                "inactive"-> "Неактивен"
                                else -> "Активирован"
                            }
                        }",
                        color = serviceCardTextColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "Цена: ${planData.planTypeCost}",
                            color = serviceCardTextColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Image(
                            modifier = Modifier
                                .padding(bottom = 3.dp),
                            painter = painterResource(id = R.drawable.service_ruble_icon),
                            contentDescription = "ruble"
                        )
                    }
                }
                Box(
                    contentAlignment = Alignment.Center
                ){
                    Button(
                        modifier = Modifier.size(100.dp, 26.dp),
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = serviceCardTextColor,
                        )
                    ) {}
                    if (!click){
                        Text(
                            text = when(planData.planStatus){
                                "inactive" -> "Активировать"
                                else -> "Отмена"
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    }else{
                        LoadingAnimation(
                            circleSize = 6.dp,
                            circleColor = BlueLight,
                            spaceBetween = 4.dp,
                            travelDistance = 6.dp
                        )
                    }
                }
            }
        }
    }
}