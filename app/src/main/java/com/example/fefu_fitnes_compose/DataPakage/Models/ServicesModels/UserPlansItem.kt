package com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels


import com.google.gson.annotations.SerializedName

data class UserPlansItem(
    @SerializedName("events_done")
    val eventsDone: Int,
    @SerializedName("exp_date")
    val expDate: String,
    @SerializedName("plan_capacity")
    val planCapacity: Int,
    @SerializedName("plan_id")
    val planId: Int,
    @SerializedName("service_name")
    val serviceName: String
)