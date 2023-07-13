package com.example.fefu_fitnes_compose.Screens.MainMenuPackage.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.UserPlans
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.convertAllEventsToUpdate
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.NewsDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
class MainMenuViewModel:ViewModel() {
    private val _currentNews = MutableLiveData<List<NewsDataModel>>()
    val currentNews:LiveData<List<NewsDataModel>> = _currentNews
    var userPlans by mutableStateOf(UserPlans())
    var userData by mutableStateOf(UserDataModel())
    var bookingEventData by mutableStateOf(mutableListOf(UpdateEventDataModel()))
    var isLoading = MutableStateFlow(false)

    fun cancelBooking(eventId: Int){
        MainRepository.cancelEventsBookingOnServer(eventId)
    }

    fun loadStaff(){
        viewModelScope.launch {
            MainRepository.getUserDataFromServer()
            MainRepository.getUserNextBookingFromServer()
            MainRepository.getActiveUserPlans()
            isLoading.value = true
            delay(3000L)
            isLoading.value = false
        }
    }

    init {
        MainRepository.getUserDataFromServer()
        MainRepository.getUserNextBookingFromServer()
        MainRepository.getActiveUserPlans()

        MainRepository.currentUser.observeForever{
            userData = it
        }

        MainRepository.userNextBooking.observeForever{
            bookingEventData = convertAllEventsToUpdate(it!!)
        }

        MainRepository.currentNews.observeForever{
            _currentNews.value = it
        }

        MainRepository.userActivePlans.observeForever{
            userPlans = it
        }
    }
}
