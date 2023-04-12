package com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer

import com.google.gson.annotations.SerializedName

data class EventAllDataModel(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("event_name")
    var eventName: String? = null,
    @SerializedName("begin_time")
    var beginTime: String? = null,
    @SerializedName("end_time")
    var endTime: String? = null,
    @SerializedName("total_spaces")
    var totalSpaces: Int? = null,
    @SerializedName("occupied_spaces")
    var occupiedSpaces: Int? = null,
    @SerializedName("event_description")
    var eventDescription: String? = null,
    @SerializedName("coach_name")
    var coachName: String? = null,
    @SerializedName("coach_phone_number")
    var coachPhoneNumber: String? = null,
    @SerializedName("coach_email")
    var coachEmail: String? = null,
    @SerializedName("service_cost")
    var serviceCost: Int? = null,
    @SerializedName("area_name")
    var areaName: String? = null,
    @SerializedName("area_photo")
    var areaPhoto: String? = null,
    @SerializedName("building_name")
    var buildingName: String? = null,
    @SerializedName("booking_status")
    var bookingStatus: String? = null
)
