package com.example.fefu_fitnes_compose.Screens.ScreenElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.ViewModel.MainMenuViewModel
import com.example.fefu_fitnes_compose.Screens.ScreenElements.Animation.LoadingAnimation
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.BlueURL
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import java.time.LocalDate

private val translateMonth = mapOf<Int, String>(
    1 to "Января",
    2 to "Февраля",
    3 to "Марта",
    4 to "Апреля",
    5 to "Мая",
    6 to "Июня",
    7 to "Июля",
    8 to "Августа",
    9 to "Сентября",
    10 to "Октября",
    11 to "Ноября",
    12 to "Декабря",
)

@Composable
fun QrEventCard(event: UpdateEventDataModel, mainMenuViewModel: MainMenuViewModel = viewModel()) {
    val openDialog = remember { mutableStateOf(false) }
    var loadingAnimationOn by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = event){
        loadingAnimationOn = false
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .clickable {
                openDialog.value = !openDialog.value
            },
        elevation = 3.dp
    ) {

        if (openDialog.value) {
            EventCardDialog(openDialog, event)
        }

        Row() {
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .height(110.dp)
                    .background(BlueLight)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(38.dp),
                        text = if(event.eventName == null) "Нет" else event.eventName!!,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.location_icon),
                            contentDescription = "loc_ic"
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = if(event.eventLocation == null) "Нет" else event.eventLocation!!,
                            fontSize = 12.sp
                        )
                    }
                    Row(
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.couch_icon),
                            contentDescription = "loc_ic"
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = if(event.couchName == null) "Нет" else event.couchName!!,
                            fontSize = 12.sp,
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = if(event.date!! == LocalDate.now())
                            "Сегодня"
                        else
                            if (event.date!! == LocalDate.now().plusDays(1))
                                "Завтра"
                            else "${event.date!!.dayOfMonth} ${translateMonth[event.date!!.month.value]}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "${event.startEventTime} - ${event.endEventTime}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )

                }
            }
        }
    }
}