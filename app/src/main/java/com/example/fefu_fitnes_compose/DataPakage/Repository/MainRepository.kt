package com.example.fefu_fitnes.dadadada.Repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import com.example.fefu_fitnes_compose.DataPakage.Models.ConfirmUserData
import com.example.fefu_fitnes_compose.DataPakage.Models.PushNewBookingDataModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ScanQrData
import com.example.fefu_fitnes_compose.DataPakage.Models.ScanUserData
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

    private val _currentNews = MutableLiveData<List<NewsDataModel>>()
    val currentNews:LiveData<List<NewsDataModel>> = _currentNews

    val registrationUserData = MutableLiveData<RegistrationFromStateModel>()


    //связь с сервером
    val currentUser = MutableLiveData<UserDataModel>().apply {
        this.value = UserDataModel()
    }

    fun getUserDataFromServer(token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                currentUser.postValue(FefuFitRetrofit.retrofitService.getUserData(mapOf("token" to token)))
            }catch (e:Exception){
                println(e)
            }
        }
    }


    val allEvents = MutableLiveData<List<EventAllDataModel>>().apply {
        this.value = listOf()
    }

    fun getEventsAllFromServer(token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                allEvents.postValue(FefuFitRetrofit.retrofitService.getEventsAll(mapOf("token" to token)))
            }catch (e:Exception){
                println(e)
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
                println(e)
            }
        }
    }

    val userNextBooking = MutableLiveData<List<EventAllDataModel>>()
    fun getUserNextBookingFromServer(token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                userNextBooking.postValue(FefuFitRetrofit.retrofitService.getUserNextBooking(mapOf("token" to token)))
            }catch (e:Exception){
                println(e)
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
                println(e)
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
                    getUserNextBookingFromServer()
                }
            }catch (e:Exception){
                println("$e")
            }
        }
    }


    // Qr cod апи
    val qrUserData = MutableLiveData<UserDataModel>()
    val qrUserNearBookingData = MutableLiveData<EventAllDataModel>()
    val qrNextBookingData = MutableLiveData<List<EventAllDataModel>>()
    private val qrUserId = MutableLiveData<Int>()

    val scanQrError = MutableLiveData<Boolean>().apply { this.value = null }
    fun pushQrCodeInServer(qrToken:String, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try{
                val result = FefuFitRetrofit.retrofitService.scanQrCode(ScanQrData(token, qrToken))
                qrUserId.postValue(result["user_id"])
                getQrUserDataFromServer(result["user_id"]!!, token)
                getQrNearBookingDataFromServer(result["user_id"]!!, token)
                getNextBookingOnServer(result["user_id"]!!, token)
                scanQrError.postValue(false)
            }catch (e:Exception){
                scanQrError.postValue(true)
                Log.d("MainRepositoryPushQr", e.toString())
            }
        }
    }

    private fun getQrUserDataFromServer(userId:Int = qrUserId.value!!, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try{
                qrUserData.postValue( FefuFitRetrofit.retrofitService.getQrUserData(ScanUserData(userId, token)))
                println(qrUserData.value)

            }catch (e:Exception){
                println(e)
            }
        }
    }

    private fun getQrNearBookingDataFromServer(userId:Int = qrUserId.value!!, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try{
                qrUserNearBookingData.postValue(FefuFitRetrofit.retrofitService.getQrNearBookingData(ScanUserData(userId, token)))
            }catch (e:Exception){
                qrUserNearBookingData.postValue(null)
                println("$e")
            }
        }
    }

    fun confirmEventOnServer(userId: Int = qrUserId.value!!, eventId: Int, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                FefuFitRetrofit.retrofitService.pushQrConfirmBookingData(ConfirmUserData(userId, eventId, token))
                qrUserNearBookingData.postValue(FefuFitRetrofit.retrofitService.getQrNearBookingData(ScanUserData(userId, token)))
            }catch (e:Exception){
                println(e)
            }
        }
    }

    fun unConfirmEventOnServer(userId: Int =  qrUserId.value!!, eventId: Int, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                FefuFitRetrofit.retrofitService.pushQrUnConfirmBookingData(ConfirmUserData(userId, eventId, token))
                qrUserNearBookingData.postValue(FefuFitRetrofit.retrofitService.getQrNearBookingData(ScanUserData(userId, token)))
            }catch (e:Exception){
                println(e)
            }
        }
    }

    private fun getNextBookingOnServer(userId: Int =  qrUserId.value!!, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                qrNextBookingData.postValue(FefuFitRetrofit.retrofitService.getQrNextBookingData(ScanUserData(userId,  token)))
            }catch (e:Exception){
                println(e)
            }
        }
    }


    init {
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