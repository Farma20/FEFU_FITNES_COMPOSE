package com.example.fefu_fitnes_compose.Screens.ServicesPackage.Elements

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.ui.theme.serviceCardColorOne
import com.example.fefu_fitnes_compose.ui.theme.serviceCardTextColor

@Composable
fun ServiceCard(
    infoName: String,
    infoDate: String,
    infoPay: String,
    infoColor: Color,
) {
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
                text = infoName,
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
                            text = "Цена: $infoPay",
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
                        modifier = Modifier.size(78.dp, 26.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = serviceCardTextColor,
                        )
                    ) {}
                    Text(
                        text = "Купить",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}