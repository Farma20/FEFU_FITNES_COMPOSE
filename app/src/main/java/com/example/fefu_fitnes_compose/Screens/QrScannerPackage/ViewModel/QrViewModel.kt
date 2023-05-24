package com.example.fefu_fitnes_compose.Screens.QrScannerPackage.ViewModel

import androidx.compose.runtime.MutableState
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

    var qrUserData: MutableState<UserDataModel?> = mutableStateOf(null)
    var qrUserNearEventData: MutableState<UpdateEventDataModel?> = mutableStateOf(null)
    var qrNextBooking: MutableState<List<UpdateEventDataModel>?> = mutableStateOf(null)

    init {
        MainRepository.qrUserData.observeForever{
            qrUserData.value = it
        }
        MainRepository.qrUserNearBookingData.observeForever {
            qrUserNearEventData.value = if (it != null)
                convertEventToUpdate(it)
            else
                null
        }

        MainRepository.qrNextBookingData.observeForever{
            qrNextBooking.value = if (it!=null)
                convertAllEventsToUpdate(it)
            else
                null
        }
    }
}
