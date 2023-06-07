package com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.AllServiceModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.Service
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.UI.Elements.ServiceCard
import com.example.fefu_fitnes_compose.Screens.ServicesPackage.ViewModel.ServicesViewModel
import com.example.fefu_fitnes_compose.ui.theme.BlueDark
import com.example.fefu_fitnes_compose.ui.theme.BlueLight
import com.example.fefu_fitnes_compose.ui.theme.serviceCardColorOne
import com.example.fefu_fitnes_compose.ui.theme.serviceCardColorThree
import com.example.fefu_fitnes_compose.ui.theme.serviceCardColorTwo
import com.example.fefu_fitnes_compose.ui.theme.standartTextColor


@Composable
fun ServiceUI(servicesViewModel: ServicesViewModel) {

    var event by remember {
        mutableStateOf(selectService(servicesViewModel.allServicesData.value, servicesViewModel.selectedId.value!!))
    }
    event = selectService(servicesViewModel.allServicesData.value, servicesViewModel.selectedId.value!!)
    var services by remember {
        mutableStateOf(event!!.plans)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        val color = listOf(serviceCardColorOne, serviceCardColorTwo, serviceCardColorThree)

        val spacerWidth = 25.dp
        val scrollState = rememberScrollState()

        Column {
            UpBar()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(horizontal = 22.dp)
            ) {
                Spacer(modifier = Modifier.height(spacerWidth))
                Text(
                    text = event!!.serviceName,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(spacerWidth))
                Text(
                    text = if(event!!.serviceDescription != null)event!!.serviceDescription.toString()else "Описания нет",
                    fontSize = 16.sp,
                    color = standartTextColor,
                )
                Spacer(modifier = Modifier.height(spacerWidth))
                Text(
                    text = "Абонементы:",
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(spacerWidth))

//                for ((id,item) in event!!.plans.withIndex()){
//                    ServiceCard(
//                        item.planTypeName,
//                        "1 месяц",
//                        item.planTypeCost.toString(),
//                        color[id%3],
//                    )
//                    Spacer(modifier = Modifier.height(13.dp))
//                }
                for (id in services.indices){
                    ServiceCard(
                        services[id],
                        "1 месяц",
                        id,
                        color[id%3],
                    )
                    Spacer(modifier = Modifier.height(13.dp))
                }
            }
        }
    }
}

fun selectService(allServices: AllServiceModel?, selectId:Int): Service? {
    var serves: Service? = null
    for (service in allServices!!){
        for (ser in service.services){
            if (ser.serviceId == selectId){
                serves = ser
                break
            }
        }
    }
    return serves
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
                    text = "Абонемент",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}