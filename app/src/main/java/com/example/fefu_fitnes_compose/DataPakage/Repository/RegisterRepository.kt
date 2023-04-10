package com.example.fefu_fitnes_compose.DataPakage.Repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.UserRegisterModel
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.NewServer.RegistrationDataModel
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.NewServer.EnterDataModel
import kotlinx.coroutines.launch

object RegisterRepository: ViewModel() {

    var userInit by mutableStateOf(false)

    private val registerUserList = MutableLiveData<MutableList<UserRegisterModel>>()

    fun pushLoginData(loginData: UserEnterModel){
        viewModelScope.launch {
            try {
                val result = FefuFitRetrofit.retrofitService.pushLoginData(loginData)
                if(result["status"] == "sucsess"){
                    userInit = true
                }

            }catch (e:Exception){
                println(e.message)
            }
        }
    }

    fun registerNewUser(registerData: UserRegisterModel){
        viewModelScope.launch {
            try {
                FefuFitRetrofit.retrofitService.registerUser(registerData)
            }
            catch (e:Exception){
                println(e.message)
            }
        }
    }

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
                if(!result["token"]!!.isEmpty()){
                    userInit = true
                }

            }catch (e:Exception){
                println(e.message)
            }
        }
    }


    //сеттеры
    fun addNewUser(userData: UserRegisterModel?){
        if (userData != null) {
            registerUserList.value?.add(userData)
        }
    }


    //геттеры
    fun getUserList(): List<UserRegisterModel>?{
        return registerUserList.value
    }

    init {
        registerUserList.value = mutableListOf(UserRegisterModel())
    }

}