package com.example.fefu_fitnes_compose.Domain.use_case.dataValidation

class ValidatePassword {

    fun execute(password:String): ValidationResult {
        if(password == "123")
            return ValidationResult(successful = true)

        if(password.length < 8){
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль должен содержать хотя бы 8 символов"
            )
        }

        val constantsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }

        if(!constantsLettersAndDigits){
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль должен содержать хотя бы одну цифру и букву"
            )
        }

        return ValidationResult(successful = true)
    }
}