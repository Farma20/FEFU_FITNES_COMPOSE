package com.example.fefu_fitnes_compose.DataPakage.Repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.UserRegisterModel
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import kotlinx.coroutines.launch

object RegisterRepository: ViewModel() {

    private var userInit = MutableLiveData<Boolean>()

    private val registerUserList = MutableLiveData<MutableList<UserRegisterModel>>()


    fun pushLoginData(loginData: UserEnterModel){
        viewModelScope.launch {
            try {
                val result = FefuFitRetrofit.retrofitService.pushLoginData(loginData)
                if(result["status"] == "sucsess"){
                    setUserInit(true)
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


    //сеттеры
    fun addNewUser(userData: UserRegisterModel?){
        if (userData != null) {
            registerUserList.value?.add(userData)
        }
    }


    fun setUserInit(bool:Boolean){
        userInit.value = bool
    }

    //геттеры
    fun getUserList(): List<UserRegisterModel>?{
        return registerUserList.value
    }
    fun getUserInit(): MutableLiveData<Boolean> {
        return userInit
    }

    init {
        registerUserList.value = mutableListOf(UserRegisterModel())
        userInit.value = false

    }

}