package com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models

import java.time.LocalDate

data class UpdateEventDataModel (
    var eventId: Int = 0,
    var eventName: String = "",
    var date: LocalDate = LocalDate.now(),
    var startEventTime:String = "",
    var endEventTime:String = "",
    var eventLocation: String = "",
    var couchName:String ="",
    var couchPhone: String = "",
    var couchEmail: String = "",
    var totalSpaces: Int = 0,
    var occupiedSpaces: Int = 0,
    var eventDescription: String = ""
)