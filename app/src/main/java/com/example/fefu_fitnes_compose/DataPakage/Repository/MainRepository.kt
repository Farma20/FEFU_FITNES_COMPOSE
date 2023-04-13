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

    val registrationUserData = MutableLiveData<RegistrationFromStateModel>()


    //связь с API
    private val gson = Gson()

    val allEvents = MutableLiveData<List<EventAllDataModel>>().apply {
        this.value = listOf()
    }

    //Новый сервер
    fun getEventsAllFromServer(token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                allEvents.postValue(FefuFitRetrofit.retrofitService.getEventsAll(mapOf("token" to token)))
            }catch (e:Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!${e}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            }
        }
    }

    val userEvents = MutableLiveData<List<EventAllDataModel>>().apply {
        this.value = listOf()
    }

    fun getEventsBookingFromServer(token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                userEvents.postValue(FefuFitRetrofit.retrofitService.getEventsBooking(mapOf("token" to token)))
            }catch (e:Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!${e}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            }
        }
    }


    fun addEventsBookingOnServer(eventId: Int ,token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                val result = FefuFitRetrofit.retrofitService.addEventsBooking(PushNewBookingDataModel(eventId, token))
                if(result["msg"] == "booking add success"){
                    getEventsAllFromServer()
                    getEventsBookingFromServer()
                }
            }catch (e:Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!${e}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            }
        }
    }

    fun cancelEventsBookingOnServer(eventId: Int, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                val result = FefuFitRetrofit.retrofitService.cancelEventsBooking(PushNewBookingDataModel(eventId, token))
                if(result["msg"] == "booking cancel success"){
                    getEventsAllFromServer()
                    getEventsBookingFromServer()
                }
            }catch (e:Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!${e}!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            }
        }
    }

    init {
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
}