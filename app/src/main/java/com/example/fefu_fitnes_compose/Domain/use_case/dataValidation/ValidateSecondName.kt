package com.example.fefu_fitnes_compose.Domain.use_case.dataValidation

class ValidateSecondName {
    fun execute(login:String): ValidationResult {
        if (login.isEmpty()){
            return ValidationResult(false, "Введите фамилию")
        }
        return ValidationResult(true)
    }
}