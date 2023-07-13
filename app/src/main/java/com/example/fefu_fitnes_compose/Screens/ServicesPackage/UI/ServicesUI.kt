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
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI.Elements.ServicesClassSportCard
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.ViewModel.ServicesViewModel
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import androidx.compose.foundation.Image
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import com.example.fefu_fitnes_compose.Screens.ScreenElements.Animation.LoadingAnimation
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun ServicesUI(navController: NavController, servicesViewModel: ServicesViewModel){

    val isLoading by servicesViewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    Surface() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UpBar()
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = servicesViewModel::loadStaff,
                indicator = {state, refreshTrigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshTrigger,
                        contentColor = BlueLight
                    )
                }
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    if (servicesViewModel.allServicesData.value != null){
                        for (service in servicesViewModel.allServicesData.value!!) {
                            println(service.categoryPhoto.toString())
                            ServicesClassSportCard(
                                navController = navController,
                                serviceName = service.categoryName,
                                serviceImage = service.categoryPhoto,
                                eventNameList = service.services,
                                servicesViewModel = servicesViewModel
                            )
                        }
                    }else{
                        Box(
                            modifier = Modifier.fillMaxSize().padding(top = 300.dp),
                            contentAlignment = Alignment.Center
                        ){
                            LoadingAnimation(
                                circleSize = 8.dp,
                                circleColor = BlueLight,
                                spaceBetween = 4.dp,
                                travelDistance = 6.dp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                }
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





