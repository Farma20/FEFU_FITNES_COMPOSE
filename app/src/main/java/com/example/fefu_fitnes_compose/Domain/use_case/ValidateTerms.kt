package com.example.fefu_fitnes_compose.Domain.use_case

import android.content.Context
import android.util.Patterns
import com.example.fefu_fitnes_compose.R

class ValidateTerms {

    fun execute(acceptedTerms:Boolean):ValidationResult{
        if(!acceptedTerms){
            return ValidationResult(
                successful = false,
                errorMessage = "Пожалуйста, примите условия"
            )
        }

        return ValidationResult(successful = true)
    }
}