package com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val userToken: String,
)