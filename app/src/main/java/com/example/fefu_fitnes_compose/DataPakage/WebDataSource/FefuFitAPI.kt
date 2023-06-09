package com.example.fefu_fitnes.adadadad.WebDataSource

import com.example.fefu_fitnes_compose.DataPakage.Models.ConfirmUserData
import com.example.fefu_fitnes_compose.DataPakage.Models.PushNewBookingDataModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ScanQrData
import com.example.fefu_fitnes_compose.DataPakage.Models.ScanUserData
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.ActivateQrPlan
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.AllServiceModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.OrderServiceModel
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.QrUserPlans
import com.example.fefu_fitnes_compose.DataPakage.Models.ServicesModels.UserPlans
import com.example.fefu_fitnes_compose.Screens.Initialization.RegistrationPackage.Models.NewServer.RegistrationDataModel
import com.example.fefu_fitnes_compose.Screens.Initialization.initializationPackage.Models.NewServer.EnterDataModel
import com.example.fefu_fitnes_compose.Screens.MainMenuPackage.Models.UserDataModel
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models.QrUserDataFool
import com.example.fefu_fitnes_compose.Screens.QrScannerPackage.Models.QrUserDataShort
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewServer.EventAllDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface FefuFitAPI {

    //Регистрация и инициализация
    @POST("/api/auth/signup")
    suspend fun registerData(@Body registerData: RegistrationDataModel):Map<String, String>

    @POST("/api/auth/login")
    suspend fun pushLogin(@Body userEnterData: EnterDataModel):Map<String,Any>

    //Данные главной страницы
    @POST("/api/user/user/view_self")
    suspend fun getUserData(@Body token:Map<String, String>):UserDataModel

    @POST("/api/booking/get_next")
    suspend fun getUserNextBooking(@Body token:Map<String, String>):List<EventAllDataModel>

    //Данные тренировок
    @POST("/api/timetable/event/user/view_all")
    suspend fun getEventsAll(@Body token:Map<String, String>):List<EventAllDataModel>

    @POST("/api/timetable/booking/user/view_all")
    suspend fun getEventsBooking(@Body token:Map<String, String>):List<EventAllDataModel>

    @POST("/api/timetable/booking/user/add")
    suspend fun addEventsBooking(@Body addBookingData:PushNewBookingDataModel):Map<String, String>

    @POST("/api/timetable/booking/user/cancel")
    suspend fun cancelEventsBooking(@Body addBookingData:PushNewBookingDataModel):Map<String, String>

    //Qr code
    @POST("/api/auth/scan_qr")
    suspend fun scanQrCode(@Body scanQrData:ScanQrData):Map<String, Int>

    @POST("/api/user/admin/view_one_short")
    suspend fun getQrUserDataShort(@Body scanUserData:ScanUserData): QrUserDataShort

    @POST("/api/user/admin/view_one_full")
    suspend fun getQrUserDataFool(@Body scanUserData:ScanUserData): QrUserDataFool

    @POST("/api/user/edit")
    suspend fun conformUser(@Body userData: QrUserDataFool):Map<String, String>

    @POST("/api/qr/get_booking")
    suspend fun getQrNearBookingData(@Body scanUserData:ScanUserData):EventAllDataModel

    @POST("/api/qr/confirm_booking")
    suspend fun pushQrConfirmBookingData(@Body confirmData:ConfirmUserData):Map<String, String>

    @POST("/api/qr/unconfirm_booking")
    suspend fun pushQrUnConfirmBookingData(@Body confirmData:ConfirmUserData):Map<String, String>

    @POST("/api/qr/get_next_bookings")
    suspend fun getQrNextBookingData(@Body scanUserData:ScanUserData):List<EventAllDataModel>

    @POST("/api/plan/view_user_plans")
    suspend fun getQrUserPlans(@Body scanUserData: ScanUserData): QrUserPlans

    @POST("/api/plan/activate")
    suspend fun activateQrUserPlan(@Body scanPlanData: ActivateQrPlan): Map<String, String>

    @POST("/api/plan/deactivate")
    suspend fun deactivateQrUserPlan(@Body scanPlanData: ActivateQrPlan): Map<String, String>

    @POST("/api/plan/view_next")
    suspend fun getActiveUserPlans(@Body token: Map<String, String>): UserPlans

    //sevices
    @POST("/api/plan/view")
    suspend fun getAllServicesData(@Body token: Map<String, String>):AllServiceModel

    @POST("/api/plan/order")
    suspend fun orderService(@Body serviceData: OrderServiceModel)

    @POST("/api/plan/unorder")
    suspend fun unOrderService(@Body serviceData: OrderServiceModel)

}