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
import com.example.fefu_fitnes.dadadada.Repository.MainRepository
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Dao.FastEnterDao
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Models.User
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Repository.DataBaseRepository
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.NewServer.RegistrationDataModel
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.NewServer.EnterDataModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException

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
                }
            }
            catch (cause:Throwable){
                when (cause) {
                    is HttpException -> {
                        val result = JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()
                        println(result)

                        if (cause.code() == 400){
                            registrationStatus = when (result["msg"]) {
                                "not an email" -> "Произошла ошибка при регистрации, несуществующая почта"
                                "wrong birthdate" -> "Произошла ошибка при регистрации, некорректная дата рождения"
                                "user already exists" -> "Произошла ошибка при регистрации, пользователь с данной почтой уже существует"
                                else -> "Произошла ошибка при регистрации, проверьте правильность введенных данных (номер телефона)"
                            }
                        }

                    }
                    else-> registrationStatus = "Произошла непредвиденная ошибка. Проверьте соединение с интернетом или свяжитесь с разработчиками"
                }

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
                    MainRepository.getUserDataFromServerBottomNav()
                }

            }catch (cause:Throwable){
                when (cause) {
                    is HttpException -> {
                        val result = JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()
                        if (cause.code() == 400)
                            initializationStatus = when(result["msg"]){
                                "user doesn't exist" -> "Пользователя c данным email не существует"
                                "wrong auth data" -> "Был введен неверный пароль"
                                else -> "Ошибка входа. Проверьте корректность введенных данных или зарегистрируйтесь"
                            }
                    }
                    else -> initializationStatus = "Ошибка входа. Проверьте соединение с интернетом или свяжитесь с разработчиками"
                }
            }
        }
    }

    init {
        registerUserList.value = mutableListOf(UserRegisterModel())
    }

    fun JSONObject.toMap(): Map<String, *> = keys().asSequence().associateWith {
        when (val value = this[it])
        {
            is JSONArray ->
            {
                val map = (0 until value.length()).associate { Pair(it.toString(), value[it]) }
                JSONObject(map).toMap().values.toList()
            }
            is JSONObject -> value.toMap()
            JSONObject.NULL -> null
            else            -> value
        }
    }

}