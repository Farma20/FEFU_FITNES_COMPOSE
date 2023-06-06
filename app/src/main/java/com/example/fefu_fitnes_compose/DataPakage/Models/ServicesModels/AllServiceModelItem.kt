package com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels


import com.google.gson.annotations.SerializedName

data class AllServiceModelItem(
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("category_photo")
    val categoryPhoto: Any,
    @SerializedName("services")
    val services: List<Service>
)