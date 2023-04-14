package com.example.fefu_fitnes_compose.DataPakage.Repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.UserRegisterModel
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Dao.FastEnterDao
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.NewServer.RegistrationDataModel
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.NewServer.EnterDataModel
import kotlinx.coroutines.launch

object RegisterRepository: ViewModel() {

    var userInit by mutableStateOf(false)

    var userToken by mutableStateOf("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwiZXhwIjoxNjkxNjQ5NDAxfQ.dh21Mqx-ydJGh3Q2_d4ib7CcCwpPtxDmFBUV6zhlsSM")

    private val registerUserList = MutableLiveData<MutableList<UserRegisterModel>>()


    //Новый сервер
    fun registrationData(registerData: RegistrationDataModel){
        viewModelScope.launch {
            try {
                FefuFitRetrofit.retrofitService.registerData(registerData)
            }
            catch (e:Exception){
                println(e.message)
            }
        }
    }

    fun pushLogin(loginData: EnterDataModel){
        viewModelScope.launch {
            try {
                val result = FefuFitRetrofit.retrofitService.pushLogin(loginData)
                if(result["status"]!! == "success"){
                    userInit = true
                    val data = result["data"] as Map<String, String>
                    userToken = data["token"]!!

                }

            }catch (e:Exception){
                println(e.message)
            }
        }
    }

    init {
        registerUserList.value = mutableListOf(UserRegisterModel())
    }

}