package com.example.fefu_fitnes_compose.Screens.TimeTablePackage

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.Yellow
import kotlinx.coroutines.launch
import java.time.LocalDate


private val convertNumInDay = mapOf<Int, String>(1 to "пн", 2 to "вт", 3 to "ср", 4 to "чт", 5 to "пт", 6 to "сб", 7 to "вс")


data class DayData(var day:LocalDate)
data class WeekData(var monthName:String, var dayLists:MutableList<DayData>)
data class MonthData(var weekList: MutableList<WeekData>) {
    fun getAllDays(): List<DayData> {
        val daysList = mutableListOf<DayData>()
        for (weekItem in weekList) {
            daysList += weekItem.dayLists
        }
        return daysList
    }
}
@OptIn(ExperimentalAnimationApi::class)
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

    var isScrollable by remember{
        mutableStateOf(true)
    }

    var weekNumber by rememberSaveable {
        mutableStateOf(0)
    }

    var selectDayIndex by rememberSaveable {
        mutableStateOf(LocalDate.now().dayOfWeek.value-1)
    }

    val yellowAnimateColor = remember {
        Animatable(Yellow)
    }

    val transparentAnimateColor = remember {
        Animatable(Color.Transparent)
    }
//    LaunchedEffect(Unit) {
//        yellowAnimateColor.animateTo(Color.Red, animationSpec = tween(1000))
//        yellowAnimateColor.animateTo(Color.Gray, animationSpec = tween(1000))
//    }

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()


    val itemSize = 42.dp
    val density = LocalDensity.current
    val itemSizePx = with(density) { itemSize.toPx() }
    val itemsScrollCount = 7

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
        ) {
            AnimatedContent(
                targetState = weeks.weekList[weekNumber].monthName,
                transitionSpec = {
                    addAnimation().using(
                        SizeTransform(clip = true)
                    )
                }
            ) { targetMonth ->
                Text(
                    text = targetMonth,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = BlueDark

                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if(isScrollable){
                        isScrollable = false
                        coroutineScope.launch {

                            if(weekNumber - 1 < 0)
                                weekNumber = 0
                            else
                                weekNumber--

                            listState.animateScrollBy(
                                value = -itemSizePx * itemsScrollCount,
                                animationSpec = tween(durationMillis = 1200)
                            )
                            isScrollable = true
                        }
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
                    modifier = Modifier.width((42 * 7).dp),
                    userScrollEnabled = false
                ){

                    items(daysCount){itemId->

                        IconButton(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape),
                            onClick = {
                                selectDayIndex = itemId
                            }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(if (itemId == selectDayIndex) yellowAnimateColor.value else transparentAnimateColor.value)
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

                }

                Row(modifier = Modifier
                    .width((42 * 7).dp)
                    .height(42.dp)
                    .background(Color.Transparent)){}
            }


            IconButton(
                onClick = {
                    if(isScrollable){
                        isScrollable = false
                        weeks = dateGenerator.addWeekToEnd(weeks)
                        daysCount = weeks.getAllDays().count()

                        weekNumber++

                        coroutineScope.launch {
                            listState.animateScrollBy(
                                value = itemSizePx * itemsScrollCount,
                                animationSpec = tween(durationMillis = 1200)
                            )
                            isScrollable = true
                        }
                    }
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

}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 800): ContentTransform {
    return slideInHorizontally(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}

