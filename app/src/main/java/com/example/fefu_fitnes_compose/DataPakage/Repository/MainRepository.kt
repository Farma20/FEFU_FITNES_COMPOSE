package com.example.fefu_fitnes.dadadada.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import com.example.fefu_fitnes_compose.DataPakage.Models.PushNewBookingDataModel
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.RegistrationFromStateModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.NewsDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewBookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.UpdateEventDataModel
import com.google.gson.Gson
import kotlinx.coroutines.launch


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

    //Новый сервер
    fun getEventsAllFromServer(token: String = RegisterRepository.userToken): MutableLiveData<List<EventAllDataModel>>{
        val result = MutableLiveData<List<EventAllDataModel>>()
        viewModelScope.launch {
            try {
                result.postValue(FefuFitRetrofit.retrofitService.getEventsAll(mapOf("token" to token)))
            }catch (e:Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!${e}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            }
        }
        return result
    }

    fun getEventsBookingFromServer(token: String = RegisterRepository.userToken): MutableLiveData<List<EventAllDataModel>>{
        val result = MutableLiveData<List<EventAllDataModel>>()
        viewModelScope.launch {
            try {
                result.postValue(FefuFitRetrofit.retrofitService.getEventsBooking(mapOf("token" to token)))
            }catch (e:Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!${e}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            }
        }
        return result
    }

    fun addEventsBookingOnServer(eventId: Int, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                val result = FefuFitRetrofit.retrofitService.addEventsBooking(PushNewBookingDataModel(eventId, token))
                if (result["msg"] == "booking add success"){}
            }catch (e:Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!${e}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            }
        }
    }

    fun cancelEventsBookingOnServer(eventId: Int, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                val result = FefuFitRetrofit.retrofitService.cancelEventsBooking(PushNewBookingDataModel(eventId, token))
                if (result["msg"] == "booking already cancelled"){}
            }catch (e:Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!${e}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            }
        }
    }






    init {
        _currentUserEvents.value = mutableListOf()

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