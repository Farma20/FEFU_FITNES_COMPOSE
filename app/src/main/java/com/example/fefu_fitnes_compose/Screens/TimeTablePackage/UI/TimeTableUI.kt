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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Repository.DataBaseRepository
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.ScreenElements.Animation.LoadingAnimation
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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun TimeTableUI(navController: NavController, timeTableViewModel: NewTimeTableViewModel){
   Surface() {
       Column() {
           val currentData = remember {
               mutableStateOf(LocalDate.now())
           }

           UpBar(currentData)
           TabLayout(currentData, timeTableViewModel)
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
private fun TabLayout(currentData: MutableState<LocalDate>, timeTableViewModel: NewTimeTableViewModel){

    val buttonList = listOf("Все занятия", "Мои занятия")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    val isLoading by timeTableViewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    var currentDayEvents by remember { mutableStateOf(selectEvents(timeTableViewModel.allEventData.value, currentData.value)) }
    var currentDayBookingEvent by remember { mutableStateOf(selectEvents(timeTableViewModel.bookingEventData.value, currentData.value))}
    currentDayEvents = selectEvents(timeTableViewModel.allEventData.value, currentData.value)
    currentDayBookingEvent = selectEvents(timeTableViewModel.bookingEventData.value, currentData.value)


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
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = timeTableViewModel::loadStaff,
                indicator = {state, refreshTrigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshTrigger,
                        contentColor = BlueLight
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ){
                    if(index == 0){
                        if (currentDayEvents == null) {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 220.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    LoadingAnimation(
                                        circleSize = 8.dp,
                                        circleColor = BlueLight,
                                        spaceBetween = 4.dp,
                                        travelDistance = 6.dp
                                    )
                                }
                            }
                        }
                        else if (currentDayEvents!!.isNotEmpty()){
                            items(currentDayEvents!!.size){id->
                                EventCard(currentDayEvents!![id], timeTableViewModel)
                            }
                        }else{
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 220.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Сегодня нет занятий",
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }
                        }
                    }
                    if (index == 1){
                        if (currentDayBookingEvent == null) {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 220.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    LoadingAnimation(
                                        circleSize = 8.dp,
                                        circleColor = BlueLight,
                                        spaceBetween = 4.dp,
                                        travelDistance = 6.dp
                                    )
                                }
                            }
                        }
                        else if (currentDayBookingEvent!!.isNotEmpty()){
                            items(currentDayBookingEvent!!.size){id->
                                EventCard(currentDayBookingEvent!![id], viewModel())
                            }
                        }else{
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 220.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = "На сегодня вы никуда не записаны",
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

fun selectEvents(allEvents: List<UpdateEventDataModel>?, day:LocalDate):List<UpdateEventDataModel>?{
    if (allEvents == null)
        return null

    val dayEventList = mutableListOf<UpdateEventDataModel>()
    for(item in allEvents){
        if (item.date == day)
            dayEventList.add(item)
    }
    return dayEventList
}

