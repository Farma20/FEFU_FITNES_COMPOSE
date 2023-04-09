package com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.Domain.use_case.ValidateEmail
import com.example.fefu_fitnes_compose.Domain.use_case.ValidatePassword
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Controllers.InitializationFormEvent
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.InitializationFormStateModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class InitializationViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword()
): ViewModel() {

    var state by mutableStateOf(InitializationFormStateModel())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: InitializationFormEvent){
        when(event){
            is InitializationFormEvent.EmailChanged->{
                state = state.copy(email = event.email)
            }
            is InitializationFormEvent.PasswordChanged->{
                state = state.copy(password = event.password)
            }
            is InitializationFormEvent.Submit->{
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        val hasErrors = listOf(emailResult, passwordResult).any { !it.successful }

        state = state.copy(
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage
        )

        val correctUser =
            MainRepository.registrationUserData.value!!.email == state.email &&
            MainRepository.registrationUserData.value!!.password == state.password

        if(hasErrors){
            return
        }

        viewModelScope.launch {
             RegisterRepository.pushLoginData(UserEnterModel(
                email = state.email,
                pass = state.password
            ))
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }
}