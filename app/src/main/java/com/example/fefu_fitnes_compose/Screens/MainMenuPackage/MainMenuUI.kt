package com.example.fefu_fitnes_compose.Screens.MainMenuPackage

import android.media.metrics.Event
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Repository.DataBaseRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.QrCode.GenerateQRCode
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.ViewModel.MainMenuViewModel
import com.example.fefu_fitnes_compose.Screens.ScreenElements.EmptyCard
import com.example.fefu_fitnes_compose.Screens.ScreenElements.EventCard
import com.example.fefu_fitnes_compose.Screens.ScreenElements.NearEventCard
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import java.time.LocalDate


@Preview(showBackground = true)
@Composable
fun MainMenuUI(mainMenuViewModel: MainMenuViewModel = viewModel()) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        UppBar(mainMenuViewModel)
        Column {
            NewsList(mainMenuViewModel)
            Text(
                modifier = Modifier
                    .padding(top = 20.dp, start = 8.dp, bottom = 8.dp),
                text = "Ближайшая запись",
                fontSize = 20.sp,
            )

            if(mainMenuViewModel.bookingEventData.isEmpty())
                EmptyCard()
            else{
                NearEventCard(event = mainMenuViewModel.bookingEventData[0])
            }


            Text(
                modifier = Modifier.padding(top=20.dp, start = 8.dp, bottom = 8.dp),
                text = "Активность",
                fontSize = 20.sp,
            )
            UserActive()
        }
    }
}

@Composable
private fun UppBar(mainMenuViewModel: MainMenuViewModel) {
    Surface(
        modifier = Modifier
            .shadow(elevation = 4.dp)
            .clip(
                RoundedCornerShape(
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp,
                )
            )


    ) {
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
                bitmap = GenerateQRCode(RegisterRepository.qrToken).asImageBitmap(),
                contentDescription = "qrCode",
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(
                modifier = Modifier.padding(10.dp),
            ) {

                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = if(mainMenuViewModel.userData.firstName == null) "Загрузка" else "Привет, ${mainMenuViewModel.userData.firstName}!",
                    color = Color.White,
                    fontSize = 25.sp
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
}

@Composable
private fun NewsList( mainMenuViewModel: MainMenuViewModel) {
    LazyRow(Modifier.padding(top = 20.dp)) {
        items(mainMenuViewModel.currentNews.value!!.size) { itemId ->
            Card(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(110.dp)
                    .height(130.dp)
                    .border(1.dp, BlueDark, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
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

                        val news by mainMenuViewModel.currentNews.observeAsState()

                        Text(
                            text = news!![itemId].textEvent,
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
private fun UserActive(){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        painter = painterResource(id = R.drawable.activ_holder_img),
        contentDescription = "holder",
        )
}