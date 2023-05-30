package com.example.fefu_fitnes_compose.Screens.QrScannerPackage.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes.dadadada.Repository.MainRepository.qrUserDataFool
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.convertAllEventsToUpdate
import com.example.fefu_fitnes_compose.Domain.use_case.dataConverters.convertEventToUpdate
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.RegistrationFromStateModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Controllers.SubmitUserEvent
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models.QrUserDataFool
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models.QrUserDataShort
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    fun onEvent(event: SubmitUserEvent){
        when(event){
            is SubmitUserEvent.FirstNameChanged->{
                qrUserDataFool.value = qrUserDataFool.value!!.copy(firstName = event.firstName)
            }
            is SubmitUserEvent.SecondNameChanged->{
                qrUserDataFool.value = qrUserDataFool.value!!.copy(secondName = event.secondName)
            }
            is SubmitUserEvent.MiddleNameChanged->{
                qrUserDataFool.value = qrUserDataFool.value!!.copy(thirdName = event.middleName)
            }
            is SubmitUserEvent.PhoneChanged->{
                qrUserDataFool.value = qrUserDataFool.value!!.copy(phoneNumber = event.phone)
            }
            is SubmitUserEvent.EmailChanged->{
                qrUserDataFool.value = qrUserDataFool.value!!.copy(email = event.email)
            }
            is SubmitUserEvent.GenderChanged->{
                qrUserDataFool.value = qrUserDataFool.value!!.copy(gender = event.gender)
            }
            is SubmitUserEvent.StatusChanged->{
                qrUserDataFool.value = qrUserDataFool.value!!.copy(status = event.status)
            }
            is SubmitUserEvent.BirthdayChanged->{
                qrUserDataFool.value = qrUserDataFool.value!!.copy(birthdate = event.birthday)
            }
            is SubmitUserEvent.TelegramId->{
                qrUserDataFool.value = qrUserDataFool.value!!.copy(telegramId = event.telegram)
            }
            is SubmitUserEvent.Submit->{

            }
        }
    }
}



