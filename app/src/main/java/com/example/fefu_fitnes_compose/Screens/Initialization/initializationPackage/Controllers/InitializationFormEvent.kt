package com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Controllers

sealed class InitializationFormEvent{
    data class EmailChanged(val email:String): InitializationFormEvent()
    data class PasswordChanged(val password:String): InitializationFormEvent()

    object Submit: InitializationFormEvent()
}
