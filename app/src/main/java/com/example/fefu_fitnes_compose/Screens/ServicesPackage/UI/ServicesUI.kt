package com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.Elements.ServicesClassSportCard

private data class ServiceData(
    val servicesName: String,
    val servicesImage: Int,
    val servicesEvent: List<String>
)


@Composable
fun ServicesUI(){

    val services = listOf(
        ServiceData(
            "Бассейн",
            R.drawable.services_pool_img,
            listOf(
                "Свободное плавание",
                "Аквааэробика",
                "Обучение плаванию взрослых",
                "Обучение плаванию детей"
            )
        ),
        ServiceData(
            "Тренажерный зал",
            R.drawable.services_workout_img,
            listOf(
                "Тренажерный зал",
                "Кроссфит",
            )
        ),
        ServiceData(
            "Групповые занятия",
            R.drawable.services_group_img,
            listOf(
                "Беговой клуб",
                "Степ- аэробика",
                "Силовая тренировка",
                "Танец живота",
                "Hot Iron",
                "Пилатес",
                "Fat burn",
                "Детская секция по фехтованию",
            )
        ),
        ServiceData(
            "Единоборства",
            R.drawable.services_fight_img,
            listOf(
                "Бокс",
                "Каратэ",
                "ММА",
            )
        ),
        ServiceData(
            "Игровые направления",
            R.drawable.services_game_img,
            listOf(
                "Настольный теннис",
                "Теннис",
            )
        ),
    )

    val scrollState = rememberScrollState()
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                for ((position, service) in services.withIndex())
                    ServicesClassSportCard(
                        position = position,
                        scrollState =scrollState,
                        serviceName = service.servicesName,
                        serviceImage = service.servicesImage,
                        eventNameList = service.servicesEvent
                    )
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}




//val scrollState = rememberScrollState()
//
//Column(
//modifier = Modifier.verticalScroll(scrollState)
//) {
//    repeat(10) { index ->
//        var cardHeight by remember { mutableStateOf(0) }
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(IntrinsicSize.Min)
//                .padding(16.dp)
//                .onGloballyPositioned {
//                    cardHeight = it.size.height
//                }
//                .clickable {
//                    val cardPosition = index * (cardHeight + 16.dp).value.toInt()
//                    val maxScroll = scrollState.maxValue - scrollState.viewportHeight
//                    val targetScroll = if (cardPosition > maxScroll) maxScroll else cardPosition
//                    LaunchedEffect(Unit) {
//                        scrollState.animateScrollTo(targetScroll)
//                    }
//                },
//            elevation = 8.dp
//        ) {
//            Text(text = "Card $index")
//            Text(text = "Some content")
//        }
//    }
//}
