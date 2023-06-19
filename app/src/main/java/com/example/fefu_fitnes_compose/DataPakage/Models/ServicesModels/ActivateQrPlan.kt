package com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels


import com.google.gson.annotations.SerializedName

data class ActivateQrPlan(
    @SerializedName("plan_id")
    val planId: Int,
    @SerializedName("token")
    val token: String
)