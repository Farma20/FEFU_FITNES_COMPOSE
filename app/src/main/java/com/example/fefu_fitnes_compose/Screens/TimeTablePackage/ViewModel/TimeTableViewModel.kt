package com.example.fefu_fitnes_compose.Screens.TimeTablePackage.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.EventDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import java.time.LocalDate

class TimeTableViewModel:ViewModel() {

    private val _allEvents = MutableLiveData<List<UpdateEventDataModel>>()
    val allEvents:LiveData<List<UpdateEventDataModel>> = _allEvents

    private val _userEvents = MutableLiveData<List<BookingDataModel>>()
    val userEvents: LiveData<List<BookingDataModel>> = _userEvents


    fun addUserEvent(bookingId: Int){
        for(event in _allEvents.value!!)
            if(event.eventId == bookingId){
                val newUserEvents = _userEvents.value!! as MutableList
                newUserEvents.add(BookingDataModel(eventId = bookingId))
                _userEvents.value = newUserEvents
                break
            }
    }

    fun setMainEvent(workout:UpdateEventDataModel){
        MainRepository.setEvent(workout)
    }

    fun pushNewBooking(bookingId: Int){
        MainRepository.pushNewBookingOnServer(bookingId)
    }

    private fun convertEvents(events: List<EventDataModel>):List<UpdateEventDataModel>{
        val updateEventDataModels = mutableListOf<UpdateEventDataModel>()
        for (event in events){

            val startDateTime:List<String> = event.beginTime.split("\\s".toRegex())
            val endDateTime: List<String> = event.endTime.split("\\s".toRegex())
            val dateList = startDateTime[0].split("-")
            val date = LocalDate.of(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())

            val uEventDataModel = UpdateEventDataModel(
                event.eventId,
                event.eventName,
                date,
                startDateTime[1].substring(0, startDateTime[1].length - 3),
                endDateTime[1].substring(0, endDateTime[1].length - 3),
                event.eventLocation,
                event.couchName,
                event.couchPhone,
                event.couchEmail,
                event.totalSpaces,
                event.occupiedSpaces,
                event.eventDescription
            )
            updateEventDataModels.add(uEventDataModel)
        }
        return updateEventDataModels
    }

    init {
        _userEvents.value = listOf(BookingDataModel())
        _allEvents.value = listOf(UpdateEventDataModel(
            0,
            "Групповое занятие по аэробике",
            LocalDate.now(),
            "10:00",
            "12:00",
            "Корпус S, зал аэробики",
            "Кердун Юлия Олеговна",
            "8 (999) 618 10 12",
            "96kerdun.iuol@dvfu.ru",
            20,
            10,
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
            )
        )

        MainRepository.getUserEventsFromSever().observeForever{
            _userEvents.value = it
        }

        MainRepository.getAllEventFromServer().observeForever{
            MainRepository.setAllEvents(convertEvents(it.toList()))
        }

        MainRepository.allEvents.observeForever{
            _allEvents.value = it
        }
    }

}
















//UpdateEventDataModel(
//0,
//"Групповое занятие по аэробике",
//LocalDate.now(),
//"10:00",
//"12:00",
//"Корпус S, зал аэробики",
//"Кердун Юлия Олеговна",
//"8 (999) 618 10 12",
//"96kerdun.iuol@dvfu.ru",
//20,
//10,
//"Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
//),UpdateEventDataModel(
//0,
//"Групповое занятие по аэробике",
//LocalDate.now(),
//"10:00",
//"12:00",
//"Корпус S, зал аэробики",
//"Кердун Юлия Олеговна",
//"8 (999) 618 10 12",
//"96kerdun.iuol@dvfu.ru",
//20,
//10,
//"Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
//),UpdateEventDataModel(
//0,
//"Групповое занятие по аэробике",
//LocalDate.now(),
//"10:00",
//"12:00",
//"Корпус S, зал аэробики",
//"Кердун Юлия Олеговна",
//"8 (999) 618 10 12",
//"96kerdun.iuol@dvfu.ru",
//20,
//10,
//"Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
//),UpdateEventDataModel(
//0,
//"Групповое занятие по аэробике",
//LocalDate.now(),
//"10:00",
//"12:00",
//"Корпус S, зал аэробики",
//"Кердун Юлия Олеговна",
//"8 (999) 618 10 12",
//"96kerdun.iuol@dvfu.ru",
//20,
//10,
//"Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
//),UpdateEventDataModel(
//0,
//"Групповое занятие по аэробике",
//LocalDate.now(),
//"10:00",
//"12:00",
//"Корпус S, зал аэробики",
//"Кердун Юлия Олеговна",
//"8 (999) 618 10 12",
//"96kerdun.iuol@dvfu.ru",
//20,
//10,
//"Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
//),UpdateEventDataModel(
//0,
//"Групповое занятие по аэробике",
//LocalDate.now(),
//"10:00",
//"12:00",
//"Корпус S, зал аэробики",
//"Кердун Юлия Олеговна",
//"8 (999) 618 10 12",
//"96kerdun.iuol@dvfu.ru",
//20,
//10,
//),
//"Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
//),
//UpdateEventDataModel(
//0,
//"Групповое занятие по аэробике",
//LocalDate.now(),
//"10:00",
//"12:00",
//"Корпус S, зал аэробики",
//"Кердун Юлия Олеговна",
//"8 (999) 618 10 12",
//"96kerdun.iuol@dvfu.ru",
//20,
//10,
//"Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
