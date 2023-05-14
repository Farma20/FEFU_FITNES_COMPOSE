package com.example.fefu_fitnes_compose.Screens.QrScannerPackage.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import java.time.LocalDate

class QrViewModel:ViewModel() {

    var qrUserData by mutableStateOf(UserDataModel())
    var qrUserNearEventData by mutableStateOf(UpdateEventDataModel())
    var qrNextBooking by mutableStateOf(listOf(UpdateEventDataModel()))

    init {
        MainRepository.qrUserData.observeForever{
            qrUserData = it
        }
        MainRepository.qrUserNearBookingData.observeForever{
            if (it != null)
                qrUserNearEventData = convertAllEventsToUpdate(it)
            else
                qrUserNearEventData = UpdateEventDataModel()
        }

        MainRepository.qrNextBookingData.observeForever{
            if (it!=null)
                qrNextBooking = convertAllListEventsToUpdate(it)
            else
                qrNextBooking = listOf()
        }
    }
}


//Преобразование входящих данных
private fun convertAllEventsToUpdate(event: EventAllDataModel): UpdateEventDataModel {

    val startDateTime: List<String> = event.beginTime!!.split("T".toRegex())
    val endDateTime: List<String> = event.endTime!!.split("T".toRegex())
    val dateList = startDateTime[0].split("-")
    val date = LocalDate.of(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())

    return UpdateEventDataModel(
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
}

private fun convertAllListEventsToUpdate(events: List<EventAllDataModel>):MutableList<UpdateEventDataModel>{
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