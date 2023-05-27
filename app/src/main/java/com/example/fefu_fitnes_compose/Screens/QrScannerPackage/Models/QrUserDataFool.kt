package com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models


import com.google.gson.annotations.SerializedName

data class QrUserDataFool(
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("user_id")
    var userId:Int? = null,
    @SerializedName("birthdate")
    var birthdate: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("telegram_id")
    val telegramId: String?,
    @SerializedName("third_name")
    val thirdName: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("verified")
    var verified: Boolean
)