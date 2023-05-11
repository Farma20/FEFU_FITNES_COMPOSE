package com.example.fefu_fitnes.adadadad.WebDataSource

import com.example.fefu_fitnes_compose.DataPakage.Models.ConfirmUserData
import com.example.fefu_fitnes_compose.DataPakage.Models.PushNewBookingDataModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ScanQrData
import com.example.fefu_fitnes_compose.DataPakage.Models.ScanUserData
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
    @POST("/api/api/auth/signup")
    suspend fun registerData(@Body registerData: RegistrationDataModel):Map<String, String>

    @POST("/api/api/auth/login")
    suspend fun pushLogin(@Body userEnterData: EnterDataModel):Map<String,Any>

    //Данные главной страницы
    @POST("/apiapi/user/user/view_self")
    suspend fun getUserData(@Body token:Map<String, String>):UserDataModel

    @POST("/api/api/booking/get_next")
    suspend fun getUserNextBooking(@Body token:Map<String, String>):List<EventAllDataModel>

    //Данные тренировок
    @POST("/api/api/timetable/event/user/view_all")
    suspend fun getEventsAll(@Body token:Map<String, String>):List<EventAllDataModel>

    @POST("/api/api/timetable/booking/user/view_all")
    suspend fun getEventsBooking(@Body token:Map<String, String>):List<EventAllDataModel>

    @POST("/api/api/timetable/booking/user/add")
    suspend fun addEventsBooking(@Body addBookingData:PushNewBookingDataModel):Map<String, String>

    @POST("/api/api/timetable/booking/user/cancel")
    suspend fun cancelEventsBooking(@Body addBookingData:PushNewBookingDataModel):Map<String, String>

    //Qr code
    @POST("/api/api/auth/scan_qr")
    suspend fun scanQrCode(@Body scanQrData:ScanQrData):Map<String, Int>

    @POST("/api/api/user/admin/view_one")
    suspend fun getQrUserData(@Body scanUserData:ScanUserData):UserDataModel

    @POST("/api/api/qr/get_booking")
    suspend fun getQrNearBookingData(@Body scanUserData:ScanUserData):EventAllDataModel

    @POST("/api/api/qr/confirm_booking")
    suspend fun pushQrConfirmBookingData(@Body confirmData:ConfirmUserData):Map<String, String>

    @POST("/api/api/qr/unconfirm_booking")
    suspend fun pushQrUnConfirmBookingData(@Body confirmData:ConfirmUserData):Map<String, String>

    @POST("/api/api/qr/get_next_bookings")
    suspend fun getQrNextBookingData(@Body scanUserData:ScanUserData):List<EventAllDataModel>
}