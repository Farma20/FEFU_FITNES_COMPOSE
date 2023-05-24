package com.example.fefu_fitnes_compose.Screens.MainMenuPackage.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.convertAllEventsToUpdate
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.NewsDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import java.time.LocalDate

@SuppressLint("MutableCollectionMutableState")
class MainMenuViewModel:ViewModel() {
    private val _currentNews = MutableLiveData<List<NewsDataModel>>()
    val currentNews:LiveData<List<NewsDataModel>> = _currentNews

    var userData by mutableStateOf(UserDataModel())
    var bookingEventData by mutableStateOf(mutableListOf(UpdateEventDataModel()))

    fun cancelBooking(eventId: Int){
        MainRepository.cancelEventsBookingOnServer(eventId)
    }

    init {
        MainRepository.getUserDataFromServer()
        MainRepository.getUserNextBookingFromServer()

        MainRepository.currentUser.observeForever{
            userData = it
        }

        MainRepository.userNextBooking.observeForever{
            bookingEventData = convertAllEventsToUpdate(it!!)
        }

        MainRepository.currentNews.observeForever{
            _currentNews.value = it
        }
    }


}
