package com.example.fefu_fitnes.dadadada.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.RegistrationFromStateModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.NewsDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.EventDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewBookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.time.LocalDate


object MainRepository: ViewModel() {

    private val _currentUser = MutableLiveData<UserDataModel>()
    val currentUser:LiveData<UserDataModel> = _currentUser

    private val _currentNews = MutableLiveData<List<NewsDataModel>>()
    val currentNews:LiveData<List<NewsDataModel>> = _currentNews

    private val _currentUserEvents = MutableLiveData<MutableList<BookingDataModel>>()
    val currentUserEvents: LiveData<MutableList<BookingDataModel>> = _currentUserEvents

    private val _allEvents = MutableLiveData<List<UpdateEventDataModel>>()
    val allEvents: LiveData<List<UpdateEventDataModel>> = _allEvents

    val registrationUserData = MutableLiveData<RegistrationFromStateModel>()




    //связь с API
    private val gson = Gson()

//    fun getUserDataFromServer(): LiveData<UserDataModel> {
//        val result = MutableLiveData<UserDataModel>()
//        viewModelScope.launch {
//            try {
//                val listResult = FefuFitRetrofit.retrofitService.getUserData()
//
//                result.postValue(gson.fromJson(listResult, UserDataModel::class.java))
//            }catch (e:Exception){
//                println(e)
//                result.postValue(UserDataModel("Юра", "Гослинг", "№583057349", "0 занятий"))
//            }
//        }
//
//        return result
//    }
//
//
//    fun getAllEventFromServer():LiveData<Array<EventDataModel>>{
//
//        val result = MutableLiveData<Array<EventDataModel>>()
//        viewModelScope.launch {
//            try {
//                val listResult = FefuFitRetrofit.retrofitService.getAllEvents()
//                result.postValue(gson.fromJson(listResult, Array<EventDataModel>::class.java))
//            }catch (e:Exception){
//                println("MainRepository нет соединения с сервером!!!!")
//            }
//        }
//        return result
//    }
//
//
//    fun getUserEventsFromSever():MutableLiveData<MutableList<BookingDataModel>>{
//        val result = MutableLiveData<MutableList<BookingDataModel>>()
//        viewModelScope.launch {
//            try {
//                result.postValue(FefuFitRetrofit.retrofitService.getUserEvents())
//            }catch (e:Exception){
//                println(e)
//            }
//        }
//        return result
//    }
//
    fun pushNewBookingOnServer(eventId: Int){
        viewModelScope.launch {
            try {
                FefuFitRetrofit.retrofitService.newBooking(NewBookingDataModel(eventId))
            }catch (e:Exception){
                println(e)
            }
        }
    }






    //Сеттеры
    fun setUser(user:UserDataModel){
        _currentUser.value = user
    }

    fun setAllEvents(workouts: List<UpdateEventDataModel>){
        _allEvents.value = workouts
    }


    fun setNews(events:List<NewsDataModel>){
        _currentNews.value = events
    }



    init {
        _currentUserEvents.value = mutableListOf()

//        getUserEventsFromSever().observeForever(){
//            _currentUserEvents.value = it
//        }
        MainRepository.
        _allEvents.value = listOf(
            UpdateEventDataModel(
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
            ),
            UpdateEventDataModel(
                1,
                "Групповое занятие по бегу",
                LocalDate.now(),
                "10:00",
                "12:00",
                "Корпус S, зал аэробики",
                "Кердун Юлия Олеговна",
                "8 (999) 618 10 12",
                "96kerdun.iuol@dvfu.ru",
                20,
                10,
                "Бег – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
            ),
            UpdateEventDataModel(
                2,
                "Групповое занятие по фехтованию",
                LocalDate.now().plusDays(1),
                "10:00",
                "12:00",
                "Корпус S, зал аэробики",
                "Кердун Юлия Олеговна",
                "8 (999) 618 10 12",
                "96kerdun.iuol@dvfu.ru",
                20,
                10,
                "Фехтование – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
            ),
            UpdateEventDataModel(
                3,
                "Групповое занятие по стретчингу",
                LocalDate.now().plusDays(1),
                "10:00",
                "12:00",
                "Корпус S, зал аэробики",
                "Кердун Юлия Олеговна",
                "8 (999) 618 10 12",
                "96kerdun.iuol@dvfu.ru",
                20,
                10,
                "Стретчинг – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
            ),
            UpdateEventDataModel(
                4,
                "Групповое занятие по плаванию",
                LocalDate.now().plusDays(1),
                "10:00",
                "12:00",
                "Корпус S, зал аэробики",
                "Кердун Юлия Олеговна",
                "8 (999) 618 10 12",
                "96kerdun.iuol@dvfu.ru",
                20,
                10,
                "Плавание – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
            ),
            UpdateEventDataModel(
                5,
                "Групповое занятие по жиму лежа",
                LocalDate.now().plusWeeks(1),
                "10:00",
                "12:00",
                "Корпус S, зал аэробики",
                "Кердун Юлия Олеговна",
                "8 (999) 618 10 12",
                "96kerdun.iuol@dvfu.ru",
                20,
                10,
                "Жим лежа – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
            ),
            UpdateEventDataModel(
                6,
                "Групповое занятие по фехтованию",
                LocalDate.now().plusWeeks(1),
                "10:00",
                "12:00",
                "Корпус S, зал аэробики",
                "Кердун Юлия Олеговна",
                "8 (999) 618 10 12",
                "96kerdun.iuol@dvfu.ru",
                20,
                10,
                "Фехтование – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка."
            )
        )

        _currentUser.value = UserDataModel("Райан", "Гослинг", "№583057349", "0 занятий")
        _currentNews.value = listOf(
            NewsDataModel("Чемпионат АССК по настольному теннису"),
            NewsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
            NewsDataModel("Чем заняться в свободное время на каникулах?"),
            NewsDataModel("Чемпионат АССК по настольному теннису"),
            NewsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
            NewsDataModel("Чем заняться в свободное время на каникулах?")
        )

        registrationUserData.value = RegistrationFromStateModel()
    }



    fun deleteUserEvent(bookingId: Int){
        for(id in _currentUserEvents.value!!.indices){
            if(_currentUserEvents.value!![id].eventId == bookingId){
                _currentUserEvents.value!!.removeAt(id)
                val newCurrentUserEvents = _currentUserEvents.value!!
                _currentUserEvents.value = newCurrentUserEvents
                break
            }
        }
    }

}