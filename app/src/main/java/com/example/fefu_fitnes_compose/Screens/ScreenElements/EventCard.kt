package com.example.fefu_fitnes_compose.Screens.ScreenElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.ViewModel.NewTimeTableViewModel
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.BlueURL
import com.example.fefu_fitnes_compose.ui.theme.Yellow

@Composable
fun EventCard(
    event: UpdateEventDataModel,
    timeTableViewModel: NewTimeTableViewModel = viewModel()
) {
    val openDialog = remember { mutableStateOf(false) }
    val selected = remember { mutableStateOf(false) }
    val occupiedSpace = remember { mutableStateOf(0) }
    occupiedSpace.value = event.occupiedSpaces!!
    selected.value = event.bookingStatus!! != "booked"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .shadow(
                elevation = 3.dp
            )
            .clickable {
                openDialog.value = !openDialog.value
            }
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
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp),
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
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ) {

                    Text(
                        text = "${event.startEventTime} - ${event.endEventTime}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Row(
                        modifier = Modifier.padding(top = 1.dp),
                    ) {
                        Text(
                            text = "Свободных мест ",
                            fontSize = 10.sp,
                        )
                        Text(
                            text = "${occupiedSpace.value}/${event.totalSpaces}",
                            fontSize = 10.sp,
                            color = BlueURL
                        )
                    }



                    TextButton(
                        onClick = {
                            if(selected.value){
                                timeTableViewModel.addNewBooking(event.eventId!!).observeForever{
                                    selected.value = !it
                                    occupiedSpace.value = occupiedSpace.value+1
                                    event.occupiedSpaces = occupiedSpace.value
                                    event.bookingStatus = "booked"
                                }
                            }
                            else
                                timeTableViewModel.cancelBooking(event.eventId!!).observeForever{
                                    selected.value = it
                                    occupiedSpace.value = occupiedSpace.value-1
                                    event.occupiedSpaces = occupiedSpace.value
                                    event.bookingStatus = "cancelled"
                                }
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = if(selected.value) Yellow else Color.Gray,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(top = 17.dp)
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
                        Text(
                            text = if(selected.value) "Записаться" else "Отменить",
                            fontSize = 14.sp
                        )
                    }

                }
            }
        }
    }
}