package com.example.fefu_fitnes_compose.Domain.use_case.dataValidation

class ValidateFirstName {
    fun execute(login:String): ValidationResult {
        if (login.isEmpty()){
            return ValidationResult(false, "Введите имя")
        }
        return ValidationResult(true)
    }
}