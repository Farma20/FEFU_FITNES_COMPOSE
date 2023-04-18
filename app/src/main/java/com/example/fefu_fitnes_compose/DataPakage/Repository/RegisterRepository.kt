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
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Models.User
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Repository.DataBaseRepository
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.NewServer.RegistrationDataModel
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.NewServer.EnterDataModel
import kotlinx.coroutines.launch

object RegisterRepository: ViewModel() {

    var userInit by mutableStateOf(false)

    var userToken by mutableStateOf("")
    var qrToken by mutableStateOf("")
    var userType by mutableStateOf("")

    var registrationStatus by mutableStateOf("")
    var initializationStatus by mutableStateOf("")

    private val registerUserList = MutableLiveData<MutableList<UserRegisterModel>>()


    //Новый сервер
    fun registrationData(registerData: RegistrationDataModel){
        viewModelScope.launch {
            try {
                val result = FefuFitRetrofit.retrofitService.registerData(registerData)
                when (result["status"]) {
                    "success" -> {
                        registrationStatus = "Поздравляем, регистрация прошла успешно"
                        pushLogin(EnterDataModel(registerData.email, registerData.password))
                    }
                    else -> {
                        when (result["msg"]) {
                            "not an email" -> registrationStatus =
                                "Произошла ошибка при регистрации, несуществующая почта"
                            "wrong birthdate" -> registrationStatus =
                                "Произошла ошибка при регистрации, некорректная дата рождения"
                            "user already exists" -> registrationStatus =
                                "Произошла ошибка при регистрации, пользователь с данной почтой уже существует"
                            else -> registrationStatus =
                                "Произошла ошибка при регистрации, непредвиденная ошибка, проверьте правильность данных"
                        }
                    }
                }
            }
            catch (e:Exception){
                registrationStatus =
                    "Произошла ошибка при регистрации, проверьте соединение с интернетом или свяжитесь с разработчиками"
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
                    qrToken = data["qr_token"]!!
                    userType = data["type"]!!
                    DataBaseRepository.get().getAllUserData().observeForever{
                        if (it.isEmpty()){
                            DataBaseRepository.get().addUserData(
                                User(
                                    userToken = userToken,
                                    qrToken = qrToken,
                                    userType = userType
                                )
                            )
                        }
                    }
                }
                else{
                    initializationStatus = "Ошибка входа. Проверьте корректность введенных данных или зарегистрируйтесь"
                }

            }catch (e:Exception){
                initializationStatus = "Ошибка входа. Проверьте соединение с интернетом или свяжитесь с разработчиками"
                println(e.message)
            }
        }
    }

    init {
        registerUserList.value = mutableListOf(UserRegisterModel())
    }

}