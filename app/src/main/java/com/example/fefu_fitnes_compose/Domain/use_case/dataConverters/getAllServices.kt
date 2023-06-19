package com.example.fefu_fitnes_compose.Domain.use_case.dataConverters

import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.AllServiceModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.Service

class getAllServices() {
    fun execute(data:AllServiceModel):List<Service>{
        val services = mutableListOf<Service>()
        for (item in data){
            for (service in item.services){
                services.add(service)
            }
        }
        return services
    }
}