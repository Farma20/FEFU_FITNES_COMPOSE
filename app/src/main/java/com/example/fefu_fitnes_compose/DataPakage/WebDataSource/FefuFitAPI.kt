package com.example.fefu_fitnes.adadadad.WebDataSource

import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel
import com.example.fefu_fitnes_compose.DataPakage.Models.AddNewBookingDataModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.NewServer.RegistrationDataModel
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.UserRegisterModel
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.NewServer.EnterDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewBookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FefuFitAPI {
    @GET("/api/user/getAllUserInfo")
    suspend fun getUserData():String

    @GET("/api/event/getAllEvent")
    suspend fun getAllEvents():String

    @GET("/api/booking/getAllBooking")
    suspend fun getUserEvents():MutableList<BookingDataModel>

    @POST("/api/booking/addNewBooking")
    suspend fun newBooking(@Body newBooking: NewBookingDataModel)


    //новый сервер

    //Регистрация и инициализация
    @POST("/api/auth/signup")
    suspend fun registerData(@Body registerData: RegistrationDataModel)

    @POST("/api/auth/login")
    suspend fun pushLogin(@Body userEnterData: EnterDataModel):Map<String,Any>

    //Данные тренировок
    @POST("/api/timetable/event/user/view_all")
    suspend fun getEventsAll(@Body token:Map<String, String>):List<EventAllDataModel>

    @POST("api/timetable/booking/user/view_all")
    suspend fun getEventsBooking(@Body token:Map<String, String>):List<EventAllDataModel>

    @POST("api/timetable/booking/user/add")
    suspend fun addEventsBooking(@Body addBookingData:AddNewBookingDataModel):Map<String, String>


}