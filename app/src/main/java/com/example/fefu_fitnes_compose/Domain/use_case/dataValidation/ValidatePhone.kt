package com.example.fefu_fitnes_compose.Domain.use_case.dataValidation

class ValidatePhone {
    fun execute(phone:String): ValidationResult {
        if(phone.isEmpty()){
            return ValidationResult(false, "Введите номер телефона")
        }
        if(!(phone.length == 12 && phone[0] == '+')&&(phone.length != 11)){
            return ValidationResult(false, "Номер телефона должен состоять из 11 символов, не учитывая символ '+'")
        }
        return ValidationResult(true)
    }
}