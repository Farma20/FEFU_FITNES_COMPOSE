package com.example.fefu_fitnes_compose.Screens.ServicesPackage.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.AllServiceModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.Service
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
class ServicesViewModel: ViewModel() {

    var allServicesData: MutableState<AllServiceModel?> = mutableStateOf(null)
    var selectedId: MutableState<Int?> = mutableStateOf(null)
    var isLoading = MutableStateFlow(false)

    fun loadStaff(){
        viewModelScope.launch {
            MainRepository.qetAllServicesDataFromServer()
            isLoading.value = true
            delay(3000L)
            isLoading.value = false
        }
    }

    init {
        MainRepository.qetAllServicesDataFromServer()

        MainRepository.allServicesData.observeForever {
            allServicesData.value = it
        }
    }
}

