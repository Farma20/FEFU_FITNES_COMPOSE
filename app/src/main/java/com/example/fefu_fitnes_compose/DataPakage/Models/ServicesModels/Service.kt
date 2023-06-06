package com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels


import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("plans")
    val plans: List<Plan>,
    @SerializedName("service_description")
    val serviceDescription: Any,
    @SerializedName("service_id")
    val serviceId: Int,
    @SerializedName("service_name")
    val serviceName: String,
    @SerializedName("service_photo")
    val servicePhoto: Any
)