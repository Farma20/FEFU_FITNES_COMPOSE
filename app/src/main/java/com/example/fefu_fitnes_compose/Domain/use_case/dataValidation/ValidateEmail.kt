package com.example.fefu_fitnes_compose.Domain.use_case.dataValidation

import android.util.Patterns

class ValidateEmail {

    fun execute(email:String): ValidationResult {
        if(email.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Почта не может быть пустой"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successful = false,
                errorMessage = "Неверная электронная почта"
            )
        }

        return ValidationResult(successful = true)
    }
}