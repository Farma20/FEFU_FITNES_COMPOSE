package com.example.fefu_fitnes_compose.DataPakage.DataBase.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class UserEnterData(
    @PrimaryKey var fastEnter:Boolean = false,
    var token:String = ""
)
