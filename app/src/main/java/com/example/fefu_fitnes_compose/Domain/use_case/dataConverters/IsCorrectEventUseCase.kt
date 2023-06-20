package com.example.fefu_fitnes_compose.Domain.use_case.dataConverters

import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.UserPlans

class IsCorrectEventUseCase {
    fun execute(eventName:String, plans:UserPlans):Boolean{
        if(plans.size != 0){
            for (plan in plans)
                if (plan.serviceName == eventName) return true
        }
        return false
    }
}