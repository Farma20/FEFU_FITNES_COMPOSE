package com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models

import com.google.gson.annotations.SerializedName

data class UserDataModel(
    @SerializedName("first_name")
    var firstName: String? = null,
    @SerializedName("second_name")
    var secondName: String? = null,
    @SerializedName("third_name")
    var thirdName: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("birthdate")
    var birthdate: String? = null,
    @SerializedName("gender")
    var gender: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("photo")
    var photo: String? = null,
    @SerializedName("phone_number")
    var phoneNumber: String? = null,
    @SerializedName("telegram_id")
    var telegramId: String? = null
)