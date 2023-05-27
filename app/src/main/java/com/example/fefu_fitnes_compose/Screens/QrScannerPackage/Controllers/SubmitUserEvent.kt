package com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Controllers

sealed class SubmitUserEvent {

    data class FirstNameChanged(val firstName:String): SubmitUserEvent()
    data class SecondNameChanged(val secondName:String): SubmitUserEvent()
    data class MiddleNameChanged(val middleName:String): SubmitUserEvent()
    data class PhoneChanged(val phone:String):  SubmitUserEvent()
    data class EmailChanged(val email:String):  SubmitUserEvent()
    data class GenderChanged(val gender:String): SubmitUserEvent()
    data class StatusChanged(val status:String): SubmitUserEvent()
    data class BirthdayChanged(val birthday: String): SubmitUserEvent()
    data class TelegramId(val telegram:String): SubmitUserEvent()

    object Submit:  SubmitUserEvent()
}