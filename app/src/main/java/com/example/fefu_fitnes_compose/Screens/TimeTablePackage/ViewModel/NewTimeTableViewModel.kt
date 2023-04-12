package com.example.fefu_fitnes_compose.Screens.TimeTablePackage.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import java.time.LocalDate

class NewTimeTableViewModel: ViewModel() {

    var allEventData by mutableStateOf(listOf(UpdateEventDataModel()))
    var bookingEventData by mutableStateOf(listOf(UpdateEventDataModel()))

    fun addNewBooking(eventId:Int){
        MainRepository.addEventsBookingOnServer(eventId)
    }

    init {
        MainRepository.getEventsAllFromServer().observeForever{
            allEventData = convertAllEventsToUpdate(it!!)
        }

        MainRepository.getEventsBookingFromServer().observeForever{
            bookingEventData = convertAllEventsToUpdate(it!!)
        }
    }
}








//Преобразование входящих данных
private fun convertAllEventsToUpdate(events: List<EventAllDataModel>):List<UpdateEventDataModel>{
    val updateEventDataModels = mutableListOf<UpdateEventDataModel>()
    for (event in events){

        val startDateTime:List<String> = event.beginTime!!.split("\\s".toRegex())
        val endDateTime: List<String> = event.endTime!!.split("\\s".toRegex())
        val dateList = startDateTime[0].split("-")
        val date = LocalDate.of(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())

        val uEventDataModel = UpdateEventDataModel(
            event.id,
            event.bookingStatus,
            event.eventName,
            date,
            startDateTime[1].substring(0, startDateTime[1].length - 3),
            endDateTime[1].substring(0, endDateTime[1].length - 3),
            event.areaName,
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