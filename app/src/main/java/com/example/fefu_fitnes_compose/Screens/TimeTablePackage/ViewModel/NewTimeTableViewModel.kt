package com.example.fefu_fitnes_compose.Screens.TimeTablePackage.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.convertAllEventsToUpdate
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

