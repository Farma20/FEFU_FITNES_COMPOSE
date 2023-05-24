package com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidateBirthday
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidateEmail
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidateFirstName
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidatePassword
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidatePhone
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidateRepeatedPassword
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidateSecondName
import com.example.fefu_fitnes_compose.Domain.use_case.dataValidation.ValidateTerms
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.RegistrationFromStateModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Controllers.RegistrationFormEvent
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.NewServer.RegistrationDataModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegistrationViewModel: ViewModel() {

    var state by mutableStateOf(RegistrationFromStateModel())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent){
        when(event){
            is RegistrationFormEvent.FirstNameChanged->{
                state = state.copy(firstName = event.firstName)
            }
            is RegistrationFormEvent.SecondNameChanged->{
                state = state.copy(secondName = event.secondName)
            }
            is RegistrationFormEvent.MiddleNameChanged->{
                state = state.copy(middleName = event.middleName)
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
            is RegistrationFormEvent.StatusChanged->{
                state = state.copy(status = event.status)
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
        val firstNameResult = ValidateFirstName().execute(state.firstName)
        val secondNameResult = ValidateSecondName().execute(state.secondName)
        val phoneResult = ValidatePhone().execute(state.phone)
        val emailResult = ValidateEmail().execute(state.email)
        val birthdayResult = ValidateBirthday().execute(state.birthday)
        val passwordResult = ValidatePassword().execute(state.password)
        val repeatPasswordResult = ValidateRepeatedPassword().execute(state.password, state.repeatPassword)
        val termsResult = ValidateTerms().execute(state.terms)

        val hasErrors = listOf(
            firstNameResult,
            secondNameResult,
            phoneResult,
            emailResult,
            passwordResult,
            repeatPasswordResult,
            termsResult,
            birthdayResult,
        ).any{!it.successful}

        state = state.copy(
            firstNameError = firstNameResult.errorMessage,
            secondNameError = secondNameResult.errorMessage,
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
            MainRepository.registrationUserData.value = state
            RegisterRepository.registrationData(
                RegistrationDataModel(
                    state.firstName,
                    state.secondName,
                    state.middleName,
                    state.email,
                    state.birthday,
                    if (state.gender) "m" else "f",
                    state.status,
                    state.phone,
                    state.password
                )
            )
            validationEventChannel.send(ValidationEvent.Success)
        }

    }

    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }

}