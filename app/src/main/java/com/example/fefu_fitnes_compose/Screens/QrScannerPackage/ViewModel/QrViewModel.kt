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
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models.QrUserDataFool
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models.QrUserDataShort
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import java.time.LocalDate

class QrViewModel:ViewModel() {

    var qrUserDataShort: MutableState<QrUserDataShort?> = mutableStateOf(null)
    var qrUserDataFool: MutableState<QrUserDataFool?> = mutableStateOf(null)
    var qrUserNearEventData: MutableState<UpdateEventDataModel?> = mutableStateOf(null)
    var qrNextBooking: MutableState<List<UpdateEventDataModel>?> = mutableStateOf(null)

    init {
        MainRepository.qrUserDataShort.observeForever{
            qrUserDataShort.value = it
        }

        MainRepository.qrUserDataFool.observeForever{
            qrUserDataFool.value = it
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
