package com.example.fefu_fitnes_compose

import android.app.Application
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Repository.DataBaseRepository

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        DataBaseRepository.initialize(this)
    }
}