package com.example.fefu_fitnes_compose.Screens.ServicesPackage.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.AllServiceModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.Service

@SuppressLint("MutableCollectionMutableState")
class ServicesViewModel: ViewModel() {

    var allServicesData: MutableState<AllServiceModel?> = mutableStateOf(null)
    var selectedService: MutableState<Service?> = mutableStateOf(null)

    init {
        MainRepository.qetAllServicesDataFromServer()

        MainRepository.allServicesData.observeForever {
            allServicesData.value = it
        }
    }
}

