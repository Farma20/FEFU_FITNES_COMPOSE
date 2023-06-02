package com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.Elements.ServiceCard
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.serviceCardColorOne
import com.example.fefu_fitnes_compose.ui.theme.serviceCardColorThree
import com.example.fefu_fitnes_compose.ui.theme.serviceCardColorTwo
import com.example.fefu_fitnes_compose.ui.theme.standartTextColor

private data class ServiceInfo(
    val infoName: String,
    val infoDate: String,
    val infoPay: String,
    val infoColor: Color,
)

@Composable
fun ServiceUI() {

    val service = listOf(
        ServiceInfo(
            "Разовое посещение",
            "1 месяц",
            "300",
            serviceCardColorOne
        ),
        ServiceInfo(
            "8 занятий",
            "1 месяц",
            "1200",
            serviceCardColorTwo
        ),
        ServiceInfo(
            "12 занятий",
            "1 месяц",
            "1600",
            serviceCardColorThree
        ),
    )

    val spacerWidth = 25.dp
    val scrollState = rememberScrollState()
    Column {
        UpBar()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(horizontal = 22.dp)
        ) {
            Spacer(modifier = Modifier.height(spacerWidth))
            Text(
                text = "Свободное плвание",
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(spacerWidth))
            Text(
                text = "Плавание – это самый безопасный вид спорта, к тому же после занятий в воде не возникает усталости. После выполнения упражнений в бассейне не бывает болей в суставах или растяжений мышц, хотя это частые травмы в других видах спорта. Правильное плавание в бассейне приносит огромную пользу организму, которую сложно переоценить. Вес тела, погруженного в воду, уменьшается, что позволяет чувствовать себя легко и по-настоящему расслабленно.",
                fontSize = 16.sp,
                color = standartTextColor,
            )
            Spacer(modifier = Modifier.height(spacerWidth))
            Text(
                text = "Абонементы:",
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(spacerWidth))

            for (item in service){
                ServiceCard(
                    item.infoName,
                    item.infoDate,
                    item.infoPay,
                    item.infoColor,
                )
                Spacer(modifier = Modifier.height(13.dp))
            }
        }
    }
}

@Composable
private fun UpBar(){
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .shadow(AppBarDefaults.TopAppBarElevation)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(BlueDark, BlueLight)
                    )
                )
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                androidx.compose.material.Text(
                    text = "Абонемент",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}