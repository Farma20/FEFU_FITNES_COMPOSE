package com.example.fefu_fitnes_compose.Domain.use_case.dataValidation

class ValidateTerms {

    fun execute(acceptedTerms:Boolean): ValidationResult {
        if(!acceptedTerms){
            return ValidationResult(
                successful = false,
                errorMessage = "Пожалуйста, примите условия"
            )
        }

        return ValidationResult(successful = true)
    }
}