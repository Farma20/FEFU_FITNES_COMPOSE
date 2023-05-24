package com.example.fefu_fitnes_compose.Domain.use_case.dataConverters

import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import java.time.LocalDate


fun convertEventToUpdate(event: EventAllDataModel): UpdateEventDataModel {

    val startDateTime: List<String> = event.beginTime!!.split("T".toRegex())
    val endDateTime: List<String> = event.endTime!!.split("T".toRegex())
    val dateList = startDateTime[0].split("-")
    val date = LocalDate.of(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())

    return UpdateEventDataModel(
        event.id,
        event.eventName,
        date,
        startDateTime[1].substring(0, startDateTime[1].length - 3),
        endDateTime[1].substring(0, endDateTime[1].length - 3),
        event.buildingName,
        event.coachName,
        event.coachPhoneNumber,
        event.coachEmail,
        event.totalSpaces,
        event.occupiedSpaces,
        event.eventDescription,
        event.status
    )
}
