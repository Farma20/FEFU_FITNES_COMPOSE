package com.example.fefu_fitnes_compose.Screens.TimeTablePackage

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun TimeTableUI(){
    UpBar()
}

@Composable
private fun UpBar(){
    Surface(
        modifier = Modifier.clip(RoundedCornerShape(
                                    bottomEnd = 10.dp,
                                    bottomStart = 10.dp))
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
            Calendar()

        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun Calendar(){

    val weeks by lazy {
        mutableStateListOf(
            listOf("Пн", "13"),
            listOf("Вт", "14"),
            listOf("Ср", "15"),
            listOf("Чт", "16"),
            listOf("Пт", "17"),
            listOf("Сб", "18"),
            listOf("Вс", "19"),
            listOf("Пн", "20"),
            listOf("Вт", "21"),
            listOf("Ср", "22"),
            listOf("Чт", "23"),
            listOf("Пт", "24"),
            listOf("Сб", "25"),
            listOf("Вс", "26"),
            listOf("Пн", "27"),
            listOf("Вт", "28"),
            listOf("Ср", "29"),
            listOf("Чт", "30"),
            listOf("Пт", "31"),
            listOf("Сб", "32"),
            listOf("Вс", "33"),
        )
    }

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    suspend fun LazyListState.disableScrolling() {
        scroll(scrollPriority = MutatePriority.PreventUserInput) {
            // Await indefinitely, blocking scrolls
            awaitCancellation()
        }
    }
    suspend fun LazyListState.enableScrolling() {
        scroll(scrollPriority = MutatePriority.PreventUserInput) {
            // Do nothing, just cancel the previous indefinite "scroll"
        }
    }
    //Отключаем прокрутку
    coroutineScope.launch {
        listState.disableScrolling()
    }

    val itemSize = 42.dp
    val density = LocalDensity.current
    val itemSizePx = with(density) { itemSize.toPx() }
    val itemsScrollCount = 7

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                coroutineScope.launch {
                    listState.enableScrolling()
                    listState.animateScrollBy(
                        value = -itemSizePx * itemsScrollCount,
                        animationSpec = tween(durationMillis = 1200)
                    )
                    listState.disableScrolling()
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
                items(weeks.count()){itemId->
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
                                text = weeks[itemId][0],
                                color = Color.White
                            )
                            Text(
                                text = weeks[itemId][1],
                                color = Color.White
                            )
                        }
                    }
                }
            }
            Row(modifier = Modifier.width((42 * 7).dp).height(42.dp).background(Color.Transparent)){}
        }


        IconButton(
            onClick = {
                  coroutineScope.launch {
                      coroutineScope.launch {
                          listState.enableScrolling()

                          listState.animateScrollBy(
                              value = itemSizePx * itemsScrollCount,
                              animationSpec = tween(durationMillis = 1200)
                          )
                          listState.disableScrolling()
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