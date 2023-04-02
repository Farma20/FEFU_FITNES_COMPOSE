package com.example.fefu_fitnes.dadadada.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.NewsDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.EventDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewBookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.google.gson.Gson
import kotlinx.coroutines.launch


object MainRepository: ViewModel() {

    private val _currentUser = MutableLiveData<UserDataModel>()
    val currentUser:LiveData<UserDataModel> = _currentUser

    private val _currentNews = MutableLiveData<List<NewsDataModel>>()
    val currentNews:LiveData<List<NewsDataModel>> = _currentNews

    private val _currentUserEvents = MutableLiveData<List<BookingDataModel>>()
    val currentUserEvents: LiveData<List<BookingDataModel>> = _currentUserEvents

    private val _currentEvent = MutableLiveData<UpdateEventDataModel>()
    val currentEvent: LiveData<UpdateEventDataModel> = _currentEvent

    private val _allEvents = MutableLiveData<List<UpdateEventDataModel>>()
    val allEvents: LiveData<List<UpdateEventDataModel>> = _allEvents


    //связь с API
    private val gson = Gson()

    fun getUserDataFromServer(): LiveData<UserDataModel> {
        val result = MutableLiveData<UserDataModel>()
        viewModelScope.launch {
            try {
                val listResult = FefuFitRetrofit.retrofitService.getUserData()

                result.postValue(gson.fromJson(listResult, UserDataModel::class.java))
            }catch (e:Exception){
                println(e)
                result.postValue(UserDataModel("Юра", "Гослинг", "№583057349", "0 занятий"))
            }
        }

        return result
    }


    fun getAllEventFromServer():LiveData<Array<EventDataModel>>{

        val result = MutableLiveData<Array<EventDataModel>>()
        viewModelScope.launch {
            try {
                val listResult = FefuFitRetrofit.retrofitService.getAllEvents()
                result.postValue(gson.fromJson(listResult, Array<EventDataModel>::class.java))
            }catch (e:Exception){
                println("MainRepository нет соединения с сервером!!!!")
            }
        }
        return result
    }


    fun getUserEventsFromSever():MutableLiveData<List<BookingDataModel>>{
        val result = MutableLiveData<List<BookingDataModel>>()
        viewModelScope.launch {
            try {
                result.postValue(FefuFitRetrofit.retrofitService.getUserEvents())
            }catch (e:Exception){
                println(e)
            }
        }
        return result
    }

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

    fun setEvent(workout:UpdateEventDataModel){
        _currentEvent.value = workout
    }

    fun setNews(events:List<NewsDataModel>){
        _currentNews.value = events
    }



    init {
        _currentUserEvents.value = listOf(BookingDataModel())
        _currentUser.value = UserDataModel("Райан", "Гослинг", "№583057349", "0 занятий")
        _currentNews.value = listOf(
            NewsDataModel("Чемпионат АССК по настольному теннису"),
            NewsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
            NewsDataModel("Чем заняться в свободное время на каникулах?"),
            NewsDataModel("Чемпионат АССК по настольному теннису"),
            NewsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
            NewsDataModel("Чем заняться в свободное время на каникулах?")
        )
        _currentEvent.value = UpdateEventDataModel()


//        allServices.value = listOf(
//            ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
//            ServicesModel("Студентам и сотрудникам", "— 3 посещения", "800"),
//            ServicesModel("Студентам и сотрудникам", "— 5 посещений", "1200"),
//            ServicesModel("Гостям кампуса", "— 3 посещения", "900"),
//            ServicesModel("Гостям кампуса", "— 5 посещений", "1400"),
//        )


    }

}