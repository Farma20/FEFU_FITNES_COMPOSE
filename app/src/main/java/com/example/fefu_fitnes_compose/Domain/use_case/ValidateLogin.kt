package com.example.fefu_fitnes_compose.Domain.use_case

class ValidateLogin {
    fun execute(login:String):ValidationResult{
        if (login.isEmpty()){
            return ValidationResult(false, "Введите логин")
        }
        return ValidationResult(true)
    }
}