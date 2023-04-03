package com.example.fefu_fitnes_compose.Screens.ScreenElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.BlueURL
import com.example.fefu_fitnes_compose.ui.theme.Yellow

@Composable
fun NearEventCard(event: UpdateEventDataModel) {
    val openDialog = remember { mutableStateOf(false) }
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
                        text = event.eventName,
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
                            text = event.eventLocation,
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
                            text = event.couchName,
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
                        text = "${event.startEventTime} - ${event.endEventTime}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )


                    TextButton(
                        onClick = {
                            MainRepository.deleteUserEvent(event.eventId)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Gray,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(top = 11.dp)
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
                            text = "Отменить",
                            fontSize = 14.sp
                        )
                    }

                }
            }
        }
    }
}