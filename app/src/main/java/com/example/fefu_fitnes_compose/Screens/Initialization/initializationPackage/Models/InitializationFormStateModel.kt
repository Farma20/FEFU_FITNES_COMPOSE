package com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models

data class InitializationFormStateModel(
    var email:String = "",
    var emailError: String? = null,
    var password:String = "",
    var passwordError:String? = null,
    val terms: Boolean = false
)