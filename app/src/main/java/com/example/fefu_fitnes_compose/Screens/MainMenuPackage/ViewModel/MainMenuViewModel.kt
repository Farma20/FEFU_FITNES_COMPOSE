package com.example.fefu_fitnes_compose.Screens.MainMenuPackage.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
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



//Преобразование входящих данных
private fun convertAllEventsToUpdate(events: List<EventAllDataModel>):MutableList<UpdateEventDataModel>{
    val updateEventDataModels = mutableListOf<UpdateEventDataModel>()
    for (event in events){

        val startDateTime:List<String> = event.beginTime!!.split("T".toRegex())
        val endDateTime: List<String> = event.endTime!!.split("T".toRegex())
        val dateList = startDateTime[0].split("-")
        val date = LocalDate.of(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())

        val uEventDataModel = UpdateEventDataModel(
            event.id,
            event.bookingStatus,
            event.eventName,
            date,
            startDateTime[1].substring(0, startDateTime[1].length - 3),
            endDateTime[1].substring(0, endDateTime[1].length - 3),
            event.buildingName,
            event.coachName,
            event.coachPhoneNumber,
            event.coachEmail,
            event.totalSpaces,
            event.occupiedSpaces,
            event.eventDescription
        )
        updateEventDataModels.add(uEventDataModel)
    }
    return updateEventDataModels
}
