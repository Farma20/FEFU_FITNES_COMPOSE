package com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.Domain.use_case.*
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.RegistrationFromStateModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Controllers.RegistrationFormEvent
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.UserRegisterModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegistrationViewModel: ViewModel() {

    var state by mutableStateOf(RegistrationFromStateModel())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent){
        when(event){
            is RegistrationFormEvent.LoginChanged->{
                state = state.copy(login = event.login)
            }
            is RegistrationFormEvent.PhoneChanged->{
                state = state.copy(phone = event.phone)
            }
            is RegistrationFormEvent.EmailChanged->{
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvent.GenderChanged->{
                state = state.copy(gender = event.gender)
            }
            is RegistrationFormEvent.BirthdayChanged->{
                state = state.copy(birthday = event.birthday)
            }
            is RegistrationFormEvent.PasswordChanged->{
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatPasswordChanged->{
                state = state.copy(repeatPassword = event.repeatPassword)
            }
            is RegistrationFormEvent.TermsChanged->{
                state = state.copy(terms = event.terms)
            }
            is RegistrationFormEvent.Submit->{
                submitData()
            }
        }
    }

    private fun submitData() {
        val loginResult = ValidateLogin().execute(state.login)
        val phoneResult = ValidatePhone().execute(state.phone)
        val emailResult = ValidateEmail().execute(state.email)
        val birthdayResult = ValidateBirthday().execute(state.birthday)
        val passwordResult = ValidatePassword().execute(state.password)
        val repeatPasswordResult = ValidateRepeatedPassword().execute(state.password, state.repeatPassword)
        val termsResult = ValidateTerms().execute(state.terms)

        val hasErrors = listOf(
            loginResult,
            phoneResult,
            emailResult,
            passwordResult,
            repeatPasswordResult,
            termsResult,
            birthdayResult,
        ).any{!it.successful}

        state = state.copy(
            loginError = loginResult.errorMessage,
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            repeatPasswordError = repeatPasswordResult.errorMessage,
            termsError = termsResult.errorMessage,
            birthdayError = birthdayResult.errorMessage,
            phoneError = phoneResult.errorMessage
        )

        if (hasErrors){
            return
        }

        viewModelScope.launch {
//            MainRepository.registrationUserData.value = state
            RegisterRepository.registerNewUser(UserRegisterModel(
                userLogin = state.login,
                userPassword = state.password,
                userEmail = state.email
            ))
            validationEventChannel.send(ValidationEvent.Success)
        }

    }

    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }

}