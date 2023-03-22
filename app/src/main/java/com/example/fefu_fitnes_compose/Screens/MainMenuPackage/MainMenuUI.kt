package com.example.fefu_fitnes_compose.Screens.MainMenuPackage

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight


private val newsItems = listOf<String>(
    "Чемпионат АССК по настольному теннису",
    "III этап зимнего сезона Студенческой Гребной Лиги",
    "Чем заняться в свободное время на каникулах?",
    "Чемпионат АССК по настольному теннису",
    "III этап зимнего сезона Студенческой Гребной Лиги",
    "Чем заняться в свободное время на каникулах?"
)

@Preview(showBackground = true)
@Composable
fun MainMenuUI() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        UppBar()
        NewsList()
        Text(
            modifier = Modifier.padding(top=20.dp, start = 8.dp, bottom = 8.dp),
            text = "Ближайшая запись",
            fontSize = 20.sp,
        )
        EventCard()
        Text(
            modifier = Modifier.padding(top=20.dp, start = 8.dp, bottom = 8.dp),
            text = "Активность",
            fontSize = 20.sp,
        )
        UserActive()
        Spacer(modifier = Modifier.padding(30.dp))
    }
}

@Composable
private fun UppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BlueDark,
                        BlueLight
                    )
                )
            ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.qr_code),
            contentDescription = "qrCode",
            modifier = Modifier
                .width(140.dp)
                .height(140.dp)
                .padding(16.dp)
        )

        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            Text(
                modifier = Modifier.padding(top= 5.dp),
                text = "Привет, Юра!",
                color = Color.White,
                fontSize = 30.sp
            )
            Text(
                modifier = Modifier.padding(top= 5.dp),
                text = "Покажи QR-код",
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "администратору",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun NewsList() {
    LazyRow(Modifier.padding(top = 20.dp)) {
        items(newsItems.size) { itemId ->
            Card(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(110.dp)
                    .border(1.dp, BlueDark, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .height(130.dp)
            ) {
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxSize(),
                    backgroundColor = BlueLight
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = newsItems[itemId],
                            color = Color.White,
                            modifier = Modifier.padding(5.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EventCard(){

    var expandedState by remember { mutableStateOf(false) }

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
                expandedState = !expandedState
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Column(
                modifier = Modifier.padding(top=8.dp, bottom = 8.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    text = "Групповое занятие по аэробике",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                )
                Row(
                    modifier = Modifier.padding(top=4.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.location_icon), contentDescription = "loc_ic")
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "Корпус S, зал аэробики",
                        fontSize = 12.sp
                    )
                }
                Row(
                    modifier = Modifier.padding(top=4.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.couch_icon), contentDescription = "loc_ic")
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "Кердун Юлия Олеговна",
                        fontSize = 12.sp,
                    )
                }
            }
            Column(
                modifier = Modifier.padding(top=8.dp, bottom = 8.dp)
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

@Composable
private fun UserActive(){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        painter = painterResource(id = R.drawable.activ_holder_img),
        contentDescription = "holder",
        )
}