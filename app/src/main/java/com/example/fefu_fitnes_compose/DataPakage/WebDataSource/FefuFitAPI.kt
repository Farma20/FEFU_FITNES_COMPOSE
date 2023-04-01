package com.example.fefu_fitnes.adadadad.WebDataSource

import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.BookingDataModel
import com.example.fefu_fitnes_compose.Screens.TimeTablePackage.Models.NewBookingDataModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FefuFitAPI {
    @GET("/api/user/getAllUserInfo")
    suspend fun getUserData():String

    @GET("/api/event/getAllEvent")
    suspend fun getAllEvents():String

    @GET("/api/booking/getAllBooking")
    suspend fun getUserEvents():List<BookingDataModel>

    @POST("/api/booking/addNewBooking")
    suspend fun newBooking(@Body newBooking: NewBookingDataModel)

//    @POST("/api/user/login")
//    suspend fun pushLoginData(@Body userEnterData:UserEnterModel):Map<String, String>

//    @POST("/api/user/signup")
//    suspend fun registerUser(@Body registerData:UserRegisterModel)

}