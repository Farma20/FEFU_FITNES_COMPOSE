package com.example.fefu_fitnes_compose.Screens.TimeTablePackage.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import java.time.LocalDate

@SuppressLint("MutableCollectionMutableState")
class NewTimeTableViewModel: ViewModel() {

    var allEventData: MutableState<List<UpdateEventDataModel>?> = mutableStateOf(null)
    var bookingEventData: MutableState<List<UpdateEventDataModel>?> = mutableStateOf(null)

    fun addNewBooking(eventId: Int) {
        MainRepository.addEventsBookingOnServer(eventId)
    }

    fun cancelBooking(eventId: Int){
        MainRepository.cancelEventsBookingOnServer(eventId)
    }


    init {
        MainRepository.getEventsAllFromServer()
        MainRepository.getEventsBookingFromServer()

        MainRepository.allEvents.observeForever{
            if (it != null)
                allEventData.value = convertAllEventsToUpdate(it)
            else
                allEventData.value = null
        }

        MainRepository.userEvents.observeForever{
            if (it != null)
                bookingEventData.value = convertAllEventsToUpdate(it)
            else
                bookingEventData.value = null
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
            event.eventDescription,
            event.event_status
        )
        updateEventDataModels.add(uEventDataModel)
    }
    return updateEventDataModels
}