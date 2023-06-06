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
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.ViewModel.ServicesViewModel
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter







private data class ServiceData(
    val servicesName: String,
    val servicesImage: Int,
    val servicesEvent: List<String>
)


@Composable
fun ServicesUI(navController: NavController, servicesViewModel: ServicesViewModel){

    Surface() {
        val photo = listOf(R.drawable.services_pool_img, R.drawable.services_workout_img, R.drawable.services_group_img, R.drawable.services_fight_img, R.drawable.services_game_img,)

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UpBar()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                if (servicesViewModel.allServicesData.value != null){
                    for (service in servicesViewModel.allServicesData.value!!) {
                        ServicesClassSportCard(
                            navController = navController,
                            serviceName = service.categoryName,
                            serviceImage = service.categoryPhoto.toString(),
                            eventNameList = service.services,
                            servicesViewModel = servicesViewModel
                        )
                    }
                }
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




