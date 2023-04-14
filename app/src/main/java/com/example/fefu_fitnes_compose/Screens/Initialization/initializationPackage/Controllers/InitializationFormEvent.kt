package com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Controllers

import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Controllers.RegistrationFormEvent

sealed class InitializationFormEvent{
    data class EmailChanged(val email:String): InitializationFormEvent()
    data class PasswordChanged(val password:String): InitializationFormEvent()
    data class TermsChanged(val terms:Boolean): InitializationFormEvent()

    object Submit: InitializationFormEvent()
}
