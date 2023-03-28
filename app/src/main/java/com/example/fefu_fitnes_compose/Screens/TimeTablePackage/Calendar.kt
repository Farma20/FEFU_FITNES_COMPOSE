package com.example.fefu_fitnes_compose.Screens.TimeTablePackage

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fefu_fitnes_compose.R
import kotlinx.coroutines.launch
import java.time.LocalDate


private val convertNumInDay = mapOf<Int, String>(1 to "пн", 2 to "вт", 3 to "ср", 4 to "чт", 5 to "пт", 6 to "сб", 7 to "вс")

private val convertNumInMonth = mapOf<Int, String>(
    1 to "Январь",
    2 to "Фервраль",
    3 to "Март",
    4 to "Апрель",
    5 to "Май",
    6 to "Июнь",
    7 to "Июль",
    8 to "Август",
    9 to "Сентябрь",
    10 to "Октябрь",
    11 to "Ноябрь",
    12 to "Декабрь",
)

data class DayData(var day:LocalDate)
data class WeekData(var monthName:String, var dayLists:MutableList<DayData>)
data class MonthData(var weekList: MutableList<WeekData>){
    fun getAllDays():List<DayData>{
        val daysList = mutableListOf<DayData>()
        for(weekItem in weekList){
            daysList += weekItem.dayLists
        }
        return daysList
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Calendar(){

    val dateGenerator = DateGenerator()

    var weeks by remember {
        mutableStateOf(dateGenerator.initData())
    }

    var daysCount by remember {
        mutableStateOf(weeks.getAllDays().size)
    }

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val itemSize = 42.dp
    val density = LocalDensity.current
    val itemSizePx = with(density) { itemSize.toPx() }
    val itemsScrollCount = 7

    weeks = dateGenerator.initData()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollBy(
                        value = -itemSizePx * itemsScrollCount,
                        animationSpec = tween(durationMillis = 1200)
                    )
                }

            },
            modifier = Modifier.size(width = 13.dp, height = 23.dp)
        ) {
            Image(
                modifier = Modifier.size(width = 13.dp, height = 23.dp),
                painter = painterResource(id = R.drawable.previous_calendar_icon),
                contentDescription = null,
            )
        }


        Box(){
            LazyRow(
                state = listState,
                modifier = Modifier.width((42 * 7).dp)
            ){

                items(daysCount){itemId->

                    IconButton(
                        modifier = Modifier.size(42.dp),
                        onClick = {}
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .size(42.dp)
                                .background(Color.Transparent)
                                .clip(CircleShape)
                        ) {
                            Text(
                                text = weeks.getAllDays()[itemId].day.dayOfMonth.toString(),
                                color = Color.White
                            )
                            Text(
                                text =  convertNumInDay[weeks.getAllDays()[itemId].day.dayOfWeek.value]!!,
                                color = Color.White
                            )
                        }
                    }
                }

                //Стартовая позиция
                coroutineScope.launch {
                    listState.scrollToItem(7)
                }
            }
            Row(modifier = Modifier
                .width((42 * 7).dp)
                .height(42.dp)
                .background(Color.Transparent)){}
        }


        IconButton(
            onClick = {



                coroutineScope.launch {
                    coroutineScope.launch {
                        listState.animateScrollBy(
                            value = itemSizePx * itemsScrollCount,
                            animationSpec = tween(durationMillis = 1200)
                        )
                    }
                }

//                weeks = dateGenerator.addWeekToEnd(weeks)
//                println(weeks)
//                daysCount = weeks.getAllDays().size

            },
            modifier = Modifier.size(width = 13.dp, height = 23.dp)
        ) {
            Image(
                modifier = Modifier.size(width = 13.dp, height = 23.dp),
                painter = painterResource(id = R.drawable.next_calendar_icon),
                contentDescription = null,
            )
        }
    }
}