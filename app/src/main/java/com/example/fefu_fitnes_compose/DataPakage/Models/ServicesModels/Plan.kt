package com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels


import com.google.gson.annotations.SerializedName

data class Plan(
    @SerializedName("plan_status")
    val planStatus: String,
    @SerializedName("plan_type_capacity")
    val planTypeCapacity: Int,
    @SerializedName("plan_type_cost")
    val planTypeCost: Int,
    @SerializedName("plan_type_id")
    val planTypeId: Int,
    @SerializedName("plan_type_name")
    val planTypeName: String
)