package com.example.fefu_fitnes_compose.Screens.QrScannerPackage.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.convertAllEventsToUpdate
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.convertEventToUpdate
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
        MainRepository.qrUserNearBookingData.observeForever {
            qrUserNearEventData = if (it != null)
                convertEventToUpdate(it)
            else
                UpdateEventDataModel()
        }

        MainRepository.qrNextBookingData.observeForever{
            qrNextBooking = if (it!=null)
                convertAllEventsToUpdate(it)
            else
                listOf()
        }
    }
}
