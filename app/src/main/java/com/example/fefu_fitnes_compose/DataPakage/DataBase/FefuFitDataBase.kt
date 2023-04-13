package com.example.fefu_fitnes_compose.DataPakage.DataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.fefu_fitnes_compose.DataPakage.DataBase.Dao.FastEnterDao
import com.example.fefu_fitnes_compose.DataPakage.DataBase.Models.UserEnterData

@Database(entities = [UserEnterData::class], version = 1)
abstract class FefuFitDataBase:RoomDatabase() {
    abstract fun fefuFitDao(): FastEnterDao
}