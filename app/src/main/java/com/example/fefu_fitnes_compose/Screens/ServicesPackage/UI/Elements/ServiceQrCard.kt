package com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI.Elements

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.Plan
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.Service
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.ScreenElements.Animation.LoadingAnimation
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.serviceCardColorOne
import com.example.fefu_fitnes_compose.ui.theme.serviceCardTextColor
import kotlinx.coroutines.delay

@Composable
fun ServiceQrCard(
    serv: Plan,
    infoDate: String,
    id: Int,
    infoColor: Color,
) {

    var status by remember { mutableStateOf(serv.planStatus) }
    var click by remember{ mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }

//    if (openDialog.value){
//        SuccessDialog(openDialog = openDialog)
//    }

    LaunchedEffect(key1 = click){
        if (click){
            delay(2000)
            click = false
            if (status != "inactive")
                openDialog.value = true
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = infoColor
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = serv.planTypeName,
                color = serviceCardTextColor,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "Длительность: $infoDate",
                        color = serviceCardTextColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "Цена: ${serv.planTypeCost}",
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
                ) {
                    Button(
                        modifier = Modifier.size(108.dp, 26.dp),
                        onClick = {
                            status = if (status == "preactive"){
                                MainRepository.serviceOrderOnServer(serv.planTypeId)
                                "active"
                            } else{
                                MainRepository.serviceUnOrderOnServer(serv.planTypeId)
                                "preactive"
                            }

                            click = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = serviceCardTextColor,
                        )
                    ) {}
                    if (!click){
                        Text(
                            text = if (status == "preactive")"Активировать" else "Отмена",
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