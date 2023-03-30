package com.example.fefu_fitnes_compose.Screens.ScreenElements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.BlueURL

@Composable
fun EventCard() {
    val openDialog = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .clickable {
                openDialog.value = !openDialog.value
            }
    ) {

        if (openDialog.value) {
            EventCardDialog(openDialog)
        }

        Row() {
            Box(
                modifier = Modifier
                    .width(7.dp)
                    .height(98.dp)
                    .background(BlueLight)
            ){

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        text = "Групповое занятие по аэробике",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                    )
                    Row(
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.location_icon),
                            contentDescription = "loc_ic"
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = "Корпус S, зал аэробики",
                            fontSize = 12.sp
                        )
                    }
                    Row(
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.couch_icon),
                            contentDescription = "loc_ic"
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = "Кердун Юлия Олеговна",
                            fontSize = 12.sp,
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "Сегодня",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "14:00 - 16:00",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.0.dp),
                        text = "Оплачено",
                        fontSize = 18.sp,
                        color = Color.Gray,
                    )
                }
            }
        }
    }
}