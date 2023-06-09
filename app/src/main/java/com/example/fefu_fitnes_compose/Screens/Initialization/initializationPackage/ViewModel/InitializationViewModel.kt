package com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidateEmail
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidatePassword
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Controllers.InitializationFormEvent
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.InitializationFormStateModel
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.NewServer.EnterDataModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class InitializationViewModel(
//    private val dao: FastEnterDao,
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

        if(hasErrors){
            return
        }

        viewModelScope.launch {
             RegisterRepository.pushLogin(
                 EnterDataModel(
                email = state.email,
                password = state.password
                )
            )
        }
    }

    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }
}