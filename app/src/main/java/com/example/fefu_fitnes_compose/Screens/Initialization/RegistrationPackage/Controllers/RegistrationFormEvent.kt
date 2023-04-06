package com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Controllers

sealed class RegistrationFormEvent {

    data class LoginChanged(val login:String): RegistrationFormEvent()
    data class PhoneChanged(val phone:String):  RegistrationFormEvent()
    data class EmailChanged(val email:String):  RegistrationFormEvent()
    data class BirthdayChanged(val birthday: String): RegistrationFormEvent()
    data class PasswordChanged(val password:String):  RegistrationFormEvent()
    data class RepeatPasswordChanged(val repeatPassword:String):  RegistrationFormEvent()

    object Submit:  RegistrationFormEvent()
}