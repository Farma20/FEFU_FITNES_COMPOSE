package com.example.fefu_fitnes_compose.Screens.MainMenuPackage.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.NewsDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel

class MainMenuViewModel:ViewModel() {
    private val _currentUser = MutableLiveData<UserDataModel>()
    val currentUser:LiveData<UserDataModel> = _currentUser

    private val _currentNews = MutableLiveData<List<NewsDataModel>>()
    val currentNews:LiveData<List<NewsDataModel>> = _currentNews

    private val _nearUserEvent = MutableLiveData<MutableList<UpdateEventDataModel>>()
    val nearUserEvent: LiveData<MutableList<UpdateEventDataModel>> = _nearUserEvent

    init {
        _currentUser.value = UserDataModel("Юра")

//        MainRepository.getUserDataFromServer().observeForever{
//            _currentUser.value = it
//        }

        MainRepository.allEvents.observeForever{
            _nearUserEvent.value = getUserEvents(it, MainRepository.currentUserEvents.value!!)
        }

        MainRepository.currentUserEvents.observeForever{
            _nearUserEvent.value = getUserEvents(MainRepository.allEvents.value!!, it)
        }

        MainRepository.currentNews.observeForever{
            _currentNews.value = it
        }
    }

    fun getUserEvents(allEvents: List<UpdateEventDataModel>, booking: List<BookingDataModel>):MutableList<UpdateEventDataModel>{
        val userEvents = mutableListOf<UpdateEventDataModel>()
        val bookingIdList = booking.map{it.eventId}
        for(item in allEvents){
            if (item.eventId in bookingIdList)
                userEvents.add(item)
        }
        return userEvents
    }
}
