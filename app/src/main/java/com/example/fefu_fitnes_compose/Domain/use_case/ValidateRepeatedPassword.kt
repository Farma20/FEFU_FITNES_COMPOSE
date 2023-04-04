package com.example.fefu_fitnes_compose.Domain.use_case

import android.content.Context
import android.util.Patterns
import com.example.fefu_fitnes_compose.R

class ValidateRepeatedPassword {

    fun execute(password:String, repeatedPassword: String):ValidationResult{
        if(password != repeatedPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "Пароли не совпадают"
            )
        }

        return ValidationResult(successful = true)
    }
}