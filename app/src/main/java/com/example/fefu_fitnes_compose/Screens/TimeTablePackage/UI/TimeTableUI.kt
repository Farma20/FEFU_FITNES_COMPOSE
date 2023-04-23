package com.example.fefu_fitnes_compose.Screens.TimeTablePackage.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Repository.DataBaseRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.ScreenElements.EventCard
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.ViewModel.NewTimeTableViewModel
import com.example.fefu_fitnes_compose.ui.theme.Blue
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.time.LocalDate

@Preview(showBackground = true)
@Composable
fun TimeTableUI(){
   Surface() {
       Column() {
           val currentData = remember {
               mutableStateOf(LocalDate.now())
           }

           UpBar(currentData)
           TabLayout(currentData)
       }
   }
}

@Composable
private fun UpBar(currentData:MutableState<LocalDate>){
    Surface(
        modifier = Modifier
            .padding(bottom = 5.dp)
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                var searchText by remember {
                    mutableStateOf("")
                }

                TextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    value = searchText,
                    textStyle = TextStyle(fontSize = 16.sp),
                    onValueChange = {searchText = it},
                    placeholder = { Text(text = "Поиск")},
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.search_icon),
                            contentDescription = null,

                            )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor=Color.White,
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.White,
                        placeholderColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                )
                Image(
                    modifier = Modifier.size(23.dp),
                    painter = painterResource(id = R.drawable.burger_menu_pick),
                    contentDescription = null,
                )
            }
            Calendar(currentData)

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabLayout(currentData: MutableState<LocalDate>, timeTableViewModel: NewTimeTableViewModel = viewModel()){

    val buttonList = listOf("Все занятия", "Мои занятия")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    var currentDayEvents by remember { mutableStateOf(selectEvents(timeTableViewModel.allEventData, currentData.value)) }
    var currentDayBookingEvent by remember { mutableStateOf(selectEvents(timeTableViewModel.bookingEventData, currentData.value))}
    currentDayEvents = selectEvents(timeTableViewModel.allEventData, currentData.value)
    currentDayBookingEvent = selectEvents(timeTableViewModel.bookingEventData, currentData.value)


    Column(
    ) {
        TabRow(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .shadow(AppBarDefaults.TopAppBarElevation),
            selectedTabIndex = tabIndex,
            indicator = {tabPosition ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPosition)
                )
            },
            backgroundColor = Blue,
            contentColor = Yellow
        ) {
            buttonList.forEachIndexed{index, text ->
                Tab(
                    selected = false,
                    onClick = {
                      coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                      }
                    },
                    text = {
                        Text(
                            text = text,
                            color = Color.White
                        )
                    }
                )
            }
        }



        HorizontalPager(
            count = buttonList.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) {index ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ){
                if(index == 0){
                    items(currentDayEvents.size){id->
                        EventCard(currentDayEvents[id])
                    }
                }
                if (index == 1){
                    items(currentDayBookingEvent.size){id->
                        EventCard(currentDayBookingEvent[id])
                    }
                }
            }
        }
    }
}

fun selectEvents(allEvents: List<UpdateEventDataModel>, day:LocalDate):List<UpdateEventDataModel>{
    val dayEventList = mutableListOf<UpdateEventDataModel>()
    for(item in allEvents){
        if (item.date == day)
            dayEventList.add(item)
    }
    return dayEventList
}

