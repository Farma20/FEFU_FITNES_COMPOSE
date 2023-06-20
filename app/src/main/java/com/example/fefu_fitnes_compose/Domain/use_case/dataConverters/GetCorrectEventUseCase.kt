package com.example.fefu_fitnes_compose.Domain.use_case.dataConverters

import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.Service

class GetCorrectEventUseCase {
    fun execute(eventName:String, allServices: List<Service>): Service?{
        for (service in allServices){
            if (service.serviceName == eventName)
                return service
        }
        return null
    }
}