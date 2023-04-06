package com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models

data class RegistrationFromStateModel(
    var login: String = "",
    var loginError: String? = null,
    var phone: String = "",
    var phoneError: String? = null,
    var email:String = "",
    var emailError: String? = null,
    var gender: Boolean = true,
    var birthday: String = "",
    var birthdayError: String? = null,
    var password:String = "",
    var passwordError:String? = null,
    var repeatPassword:String = "",
    var repeatPasswordError:String? = null
)
