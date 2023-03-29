package com.example.fefu_fitnes_compose.Screens.TimeTablePackage

import java.time.LocalDate

private val convertNumInMonth = mapOf<Int, String>(
    1 to "Январь",
    2 to "Фервраль",
    3 to "Март",
    4 to "Апрель",
    5 to "Май",
    6 to "Июнь",
    7 to "Июль",
    8 to "Август",
    9 to "Сентябрь",
    10 to "Октябрь",
    11 to "Ноябрь",
    12 to "Декабрь",
)

class DateGenerator() {

    fun initData():MonthData{
        val currentDate = LocalDate.now()
        val monday = currentDate.minusDays((currentDate.dayOfWeek.value - 1).toLong())

        val initMonthData = MonthData(mutableListOf())

        initMonthData.weekList.add(weekGenerate(monday.minusDays(7)))
        initMonthData.weekList.add(weekGenerate(monday))
        initMonthData.weekList.add(weekGenerate(monday.plusDays(7)))

        return initMonthData
    }

    fun addWeekToStart(monthData: MonthData):MonthData {
        val updateMonthData = monthData.copy()
        updateMonthData.weekList.add(0,
            weekGenerate(
                monthData
                    .weekList[monthData.weekList.size - 1]
                    .dayLists[0]
                    .day
                    .plusDays(7)
            )
        )
        return updateMonthData
    }

    fun addWeekToEnd(monthData: MonthData):MonthData {
        val updateMonthData = monthData.copy()
        updateMonthData.weekList.add(
            weekGenerate(
                monthData
                    .weekList[monthData.weekList.size - 1]
                    .dayLists[0]
                    .day
                    .plusDays(7)
            )
        )
        return updateMonthData
    }

    private fun weekGenerate(_monday_:LocalDate):WeekData{
        val week = WeekData(convertNumInMonth[_monday_.month.value]!!, mutableListOf())
        for (day in 1..7){
            val dayDate = _monday_.plusDays(day.toLong() - 1)

            week.dayLists.add(DayData(dayDate))
        }
        return week
    }

}