package com.example.fefu_fitnes.dadadada.Repository

import android.util.Log
import androidx.lifecycle.*
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import com.example.fefu_fitnes_compose.DataPakage.Models.ConfirmUserData
import com.example.fefu_fitnes_compose.DataPakage.Models.PushNewBookingDataModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ScanQrData
import com.example.fefu_fitnes_compose.DataPakage.Models.ScanUserData
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.AllServiceModel
import com.example.fefu_fitnes_compose.DataPakage.Repository.RegisterRepository
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.RegistrationFromStateModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.NewsDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models.QrUserDataFool
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models.QrUserDataShort
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
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


    val allEvents = MutableLiveData<List<EventAllDataModel>>()
    fun getEventsAllFromServer(token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                allEvents.postValue(FefuFitRetrofit.retrofitService.getEventsAll(mapOf("token" to token)))
            }catch (e:Exception){
                println(e)
            }
        }
    }

    val userEvents = MutableLiveData<List<EventAllDataModel>>()

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
    val qrUserDataShort = MutableLiveData<QrUserDataShort>()
    val qrUserDataFool = MutableLiveData<QrUserDataFool>()
    val qrUserNearBookingData = MutableLiveData<EventAllDataModel>()
    val qrNextBookingData = MutableLiveData<List<EventAllDataModel>>()
    private val qrUserId = MutableLiveData<Int>()

    val scanQrError = MutableLiveData<Boolean>().apply { this.value = null }
    fun pushQrCodeInServer(qrToken:String, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try{
                val result = FefuFitRetrofit.retrofitService.scanQrCode(ScanQrData(token, qrToken))
                qrUserId.postValue(result["user_id"])
                getQrUserDataShortFromServer(result["user_id"]!!, token)
                getQrNearBookingDataFromServer(result["user_id"]!!, token)
                getNextBookingOnServer(result["user_id"]!!, token)
                scanQrError.postValue(false)
            }catch (e:Exception){
                scanQrError.postValue(true)
                Log.d("MainRepositoryPushQr", e.toString())
            }
        }
    }

    private fun getQrUserDataShortFromServer(userId:Int = qrUserId.value!!, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try{
                qrUserDataShort.postValue( FefuFitRetrofit.retrofitService.getQrUserDataShort(ScanUserData(userId, token)))
                println(qrUserDataShort.value)

            }catch (e:Exception){
                println(e)
            }
        }
    }

    fun conformUserInServer(userDataFool: QrUserDataFool, userId:Int = qrUserId.value!!, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                userDataFool.apply {
                    this.token = token
                    this.userId = userId
                }
                val result = FefuFitRetrofit.retrofitService.conformUser(userDataFool)
                if (result["detail"] == "edit success"){
                    getQrUserDataShortFromServer()
                    getQrUserDataFoolFromServer()
                }
            }catch (e:Exception){
                println(e)
            }
        }
    }

    fun getQrUserDataFoolFromServer(userId:Int = qrUserId.value!!, token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try{
                qrUserDataFool.postValue( FefuFitRetrofit.retrofitService.getQrUserDataFool(ScanUserData(userId, token)))
                println(qrUserDataFool.value)
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

    //Services function

    val allServicesData = MutableLiveData<AllServiceModel>()
    fun qetAllServicesDataFromServer(token: String = RegisterRepository.userToken){
        viewModelScope.launch {
            try {
                allServicesData.postValue(FefuFitRetrofit.retrofitService.getAllServicesData(mapOf("token" to token)))
            }
            catch (e:Exception){
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

        qetAllServicesDataFromServer()

        registrationUserData.value = RegistrationFromStateModel()
    }
}

