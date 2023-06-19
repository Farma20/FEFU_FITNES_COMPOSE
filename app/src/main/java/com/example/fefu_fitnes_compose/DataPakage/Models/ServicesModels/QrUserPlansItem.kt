package com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels


import com.google.gson.annotations.SerializedName

data class QrUserPlansItem(
    @SerializedName("plan_id")
    val planId: Int,
    @SerializedName("plan_status")
    val planStatus: String,
    @SerializedName("plan_type_capacity")
    val planTypeCapacity: Int,
    @SerializedName("plan_type_cost")
    val planTypeCost: Int,
    @SerializedName("service_name")
    val serviceName: String
)