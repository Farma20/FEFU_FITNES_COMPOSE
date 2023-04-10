package com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models

data class RegistrationFromStateModel(
    var firstName: String = "",
    var firstNameError: String? = null,
    var secondName: String = "",
    var secondNameError: String? = null,
    var middleName: String = "",
    var phone: String = "",
    var phoneError: String? = null,
    var email:String = "",
    var emailError: String? = null,
    var gender: Boolean = true,
    var status: String = "student",
    var birthday: String = "",
    var birthdayError: String? = null,
    var password:String = "",
    var passwordError:String? = null,
    var repeatPassword:String = "",
    var repeatPasswordError:String? = null,
    var terms: Boolean = false,
    var termsError: String? = null
)
