package com.example.fefu_fitnes_compose.Screens.ScreenElements

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
import com.example.fefu_fitnes_compose.ui.theme.BlueURL

@Composable
fun EventCard() {
    var openDialog by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(start = 8.dp, end = 8.dp)
            .clickable {
                openDialog = !openDialog
            }
    ) {

        if (openDialog) {
            Dialog(
                onDismissRequest = { openDialog = false }
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Групповое занятие по аэробике",
                                modifier = Modifier.fillMaxWidth(0.6f),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Column(

                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 4.dp),
                                    text = "14:00 - 16:00",
                                    fontSize = 16.sp,
                                    maxLines = 1,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "Оплачено",
                                    fontSize = 18.sp,
                                    color = Color.Gray,
                                )
                            }

                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .padding(horizontal = 8.dp)
                                .background(Color.Black)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.couch_icon),
                                    contentDescription = "CouchIcon",
                                    modifier = Modifier.size(25.dp)
                                )
                                Column() {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(0.55f),
                                        text = "Кердун Юлия Олеговна",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.stars),
                                        contentDescription = "CouchRating"
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier.padding(top = 2.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.phone_icon),
                                        contentDescription = "phone_icon",
                                        modifier = Modifier
                                            .size(11.dp)
                                            .padding(end = 2.dp)
                                    )
                                    Text(
                                        text = "8 (999) 618 10 12",
                                        fontSize = 11.sp,
                                        color = BlueURL
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.mail_icon),
                                        contentDescription = "phone_icon",
                                        modifier = Modifier
                                            .size(11.dp)
                                            .padding(end = 2.dp)
                                    )
                                    Text(
                                        text = "96kerdun.iuol@dvfu.ru", fontSize = 11.sp,
                                        color = BlueURL
                                    )
                                }

                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .padding(horizontal = 8.dp)
                                .background(Color.Black)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.location_icon),
                                contentDescription = "locationIcon",
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(30.dp)
                                    .padding(end = 4.dp)
                            )
                            Text(
                                text = "Корпус S, зал аэробики",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            text = "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка. ",
                            fontSize = 13.sp,
                        )

                        val image = listOf(
                            R.drawable.workout_photo_1,
                            R.drawable.workout_photo_2,
                            R.drawable.workout_photo_3,
                            R.drawable.workout_photo_4,
                            R.drawable.workout_photo_5,)

                        LazyRow(
                            modifier = Modifier.padding(8.dp)
                        ){
                            items(5) {id->
                                Image(
                                    painter = painterResource(id = image[id]),
                                    contentDescription = "photo",
                                    modifier = Modifier
                                        .width(105.dp)
                                        .height(70.dp)
                                        .padding(end = 4.dp)
                                        .clip(
                                            RoundedCornerShape(7.dp)
                                        ),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
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