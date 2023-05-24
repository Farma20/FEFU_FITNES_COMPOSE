package com.example.fefu_fitnes_compose.Domain.use_case.dataValidation

class ValidateRepeatedPassword {

    fun execute(password:String, repeatedPassword: String): ValidationResult {
        if(password != repeatedPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "Пароли не совпадают"
            )
        }

        return ValidationResult(successful = true)
    }
}