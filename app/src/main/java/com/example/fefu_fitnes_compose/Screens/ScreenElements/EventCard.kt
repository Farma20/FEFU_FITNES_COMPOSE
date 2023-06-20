package com.example.fefu_fitnes_compose.Screens.ScreenElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.ScreenElements.Animation.LoadingAnimation
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.ViewModel.NewTimeTableViewModel
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.BlueURL
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import java.time.LocalDate


@Composable
fun EventCard(
    event: UpdateEventDataModel,
    timeTableViewModel: NewTimeTableViewModel
) {
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
            ){

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start=8.dp),
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
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 8.dp),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "${event.startEventTime} - ${event.endEventTime}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Row(
                        modifier = Modifier.padding(top = 1.dp, start = 4.dp),
                    ) {
                        Text(
                            text = "Мест занято ",
                            fontSize = 10.sp,
                        )
                        Text(
                            text = "${event.occupiedSpaces}/${event.totalSpaces}",
                            fontSize = 10.sp,
                            color = BlueURL
                        )
                    }

                    //variable storing button status
                    val buttonActive = !(event.status == "inactive" || event.status == "done" || event.status == "full")

                    Button(
                        enabled = buttonActive,
                        onClick = {
                            loadingAnimationOn = true
                            if(event.status == "not booked"){
                                timeTableViewModel.addNewBooking(event.eventId!!, event.eventName!!)
                            }
                            else
                                timeTableViewModel.cancelBooking(event.eventId!!)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor =
                            if(!buttonActive)
                                Color.White
                            else if (event.status == "not booked")
                                Yellow
                            else
                                Color.Gray,
                            contentColor = Color.White,
                            disabledContentColor = if (event.status == "done") BlueLight else Color.Gray
                        ),
                        modifier = Modifier
                            .padding(top = 28.dp)
                            .width(100.dp)
                            .height(30.dp),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(
                            start = 4.dp,
                            top = 0.dp,
                            end = 4.dp,
                            bottom = 0.dp
                        )
                    ) {
                        if (!loadingAnimationOn){
                            Text(
                                text = when(event.status) {
                                    "done" -> "Посещено"
                                    "not booked" -> "Записаться"
                                    "full" -> "Нет мест"
                                    "booked" -> "Отменить"
                                    "inactive" -> "Нет записи"
                                    else -> "Пипка"
                                },
                                fontSize = 14.sp
                            )
                        }else{
                            LoadingAnimation(
                                circleSize = 6.dp,
                                circleColor = Color.White,
                                spaceBetween = 4.dp,
                                travelDistance = 6.dp
                            )
                        }

                    }

                }
            }
        }
    }
}
