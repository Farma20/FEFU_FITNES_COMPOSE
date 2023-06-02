package com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fefu_fitnes_compose.R
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.Elements.ServicesClassSportCard
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight

private data class ServiceData(
    val servicesName: String,
    val servicesImage: Int,
    val servicesEvent: List<String>
)


@Composable
fun ServicesUI(navController: NavController){

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
            UpBar()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                for ((position, service) in services.withIndex())
                    ServicesClassSportCard(
                        navController = navController,
                        serviceName = service.servicesName,
                        serviceImage = service.servicesImage,
                        eventNameList = service.servicesEvent
                    )
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}

@Composable
private fun UpBar(){
    Surface(
        modifier = Modifier
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

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                androidx.compose.material.Text(
                    text = "Абонементы",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}





