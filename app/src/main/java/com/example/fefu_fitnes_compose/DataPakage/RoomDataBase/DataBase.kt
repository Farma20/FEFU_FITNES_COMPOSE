package com.example.fefu_fitnes_compose.DataPakage.RoomDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Dao.FastEnterDao
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Models.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class DataBase:RoomDatabase() {
    abstract val dao :FastEnterDao
}