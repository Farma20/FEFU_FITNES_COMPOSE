package com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Controllers

sealed class RegistrationFormEvent {

    data class FirstNameChanged(val firstName:String): RegistrationFormEvent()
    data class SecondNameChanged(val secondName:String): RegistrationFormEvent()
    data class MiddleNameChanged(val middleName:String): RegistrationFormEvent()
    data class PhoneChanged(val phone:String):  RegistrationFormEvent()
    data class EmailChanged(val email:String):  RegistrationFormEvent()
    data class GenderChanged(val gender:Boolean): RegistrationFormEvent()
    data class StatusChanged(val status:String): RegistrationFormEvent()
    data class BirthdayChanged(val birthday: String): RegistrationFormEvent()
    data class PasswordChanged(val password:String):  RegistrationFormEvent()
    data class RepeatPasswordChanged(val repeatPassword:String):  RegistrationFormEvent()
    data class TermsChanged(val terms:Boolean):RegistrationFormEvent()

    object Submit:  RegistrationFormEvent()
}