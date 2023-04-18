package com.example.fefu_fitnes.adadadad.WebDataSource

import com.example.fefu_fitnes_compose.DataPakage.Models.PushNewBookingDataModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.NewServer.RegistrationDataModel
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.NewServer.EnterDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewBookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FefuFitAPI {

    //Регистрация и инициализация
    @POST("/api/auth/signup")
    suspend fun registerData(@Body registerData: RegistrationDataModel):Map<String, String>

    @POST("/api/auth/login")
    suspend fun pushLogin(@Body userEnterData: EnterDataModel):Map<String,Any>

    //Данные главной страницы
    @POST("api/user/user/view_self")
    suspend fun getUserData(@Body token:Map<String, String>):UserDataModel

    //Данные тренировок
    @POST("/api/timetable/event/user/view_all")
    suspend fun getEventsAll(@Body token:Map<String, String>):List<EventAllDataModel>

    @POST("api/timetable/booking/user/view_all")
    suspend fun getEventsBooking(@Body token:Map<String, String>):List<EventAllDataModel>

    @POST("api/timetable/booking/user/add")
    suspend fun addEventsBooking(@Body addBookingData:PushNewBookingDataModel):Map<String, String>

    @POST("api/timetable/booking/user/cancel")
    suspend fun cancelEventsBooking(@Body addBookingData:PushNewBookingDataModel):Map<String, String>



}