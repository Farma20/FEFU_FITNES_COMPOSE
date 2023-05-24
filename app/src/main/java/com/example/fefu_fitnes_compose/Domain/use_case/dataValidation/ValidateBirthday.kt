package com.example.fefu_fitnes_compose.Domain.use_case.dataValidation

class ValidateBirthday {
    fun execute(date: String): ValidationResult {
        if (date.isEmpty()){
            return ValidationResult(false, "Выберите дату рождения")
        }
        return ValidationResult(true)
    }
}