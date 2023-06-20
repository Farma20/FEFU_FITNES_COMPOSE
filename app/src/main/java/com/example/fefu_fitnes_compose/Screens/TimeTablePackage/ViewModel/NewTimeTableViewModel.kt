package com.example.fefu_fitnes_compose.Screens.TimeTablePackage.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.Service
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.UserPlans
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.convertAllEventsToUpdate
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.GetAllServicesUseCase
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.GetCorrectEventUseCase
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.IsCorrectEventUseCase
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Navigation.TimeTableScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
class NewTimeTableViewModel(private val navController: NavController): ViewModel() {

    private val getServicesUseCase = GetAllServicesUseCase()
    private val isCorrectEventUseCase = IsCorrectEventUseCase()
    private val getCorrectEventUseCase = GetCorrectEventUseCase()

    var allEventData: MutableState<List<UpdateEventDataModel>?> = mutableStateOf(null)
    var bookingEventData: MutableState<List<UpdateEventDataModel>?> = mutableStateOf(null)
    var userPlans by mutableStateOf(UserPlans())
    var allPlans: MutableState<List<Service>> = mutableStateOf(listOf())
    var currentService :MutableState<Service?> = mutableStateOf(null)
    var isLoading = MutableStateFlow(false)

    fun addNewBooking(eventId: Int, eventName: String) {
        if (isCorrectEventUseCase.execute(eventName, userPlans)){
            MainRepository.addEventsBookingOnServer(eventId)
        }
        else{
            currentService.value = getCorrectEventUseCase.execute(eventName, allPlans.value)
            if (currentService.value != null){
                navController.navigate(TimeTableScreen.ServicesView.route)
            }
        }
    }

    fun cancelBooking(eventId: Int){
        MainRepository.cancelEventsBookingOnServer(eventId)
    }

    fun loadStaff(){
        viewModelScope.launch {
            MainRepository.getEventsAllFromServer()
            MainRepository.getEventsBookingFromServer()
            isLoading.value = true
            delay(3000L)
            isLoading.value = false
        }
    }




    init {
        MainRepository.getEventsAllFromServer()
        MainRepository.getEventsBookingFromServer()
        MainRepository.qetAllServicesDataFromServer()

        MainRepository.allEvents.observeForever{
            if (it != null)
                allEventData.value = convertAllEventsToUpdate(it)
            else
                allEventData.value = null
        }

        MainRepository.allServicesData.observeForever{
            allPlans.value = getServicesUseCase.execute(it)
        }

        MainRepository.userEvents.observeForever{
            if (it != null)
                bookingEventData.value = convertAllEventsToUpdate(it)
            else
                bookingEventData.value = null
        }

        MainRepository.userActivePlans.observeForever{
            userPlans = it
        }

    }
}

