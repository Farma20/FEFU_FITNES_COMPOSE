package com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models


import com.google.gson.annotations.SerializedName

data class QrUserDataShort(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("third_name")
    val thirdName: String?,
    @SerializedName("verified")
    val verified: Boolean
)