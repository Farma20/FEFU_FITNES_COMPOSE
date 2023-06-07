package com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels


import com.google.gson.annotations.SerializedName

data class OrderServiceModel(
    @SerializedName("plan_type_id")
    val planTypeId: Int,
    @SerializedName("token")
    val token: String
)