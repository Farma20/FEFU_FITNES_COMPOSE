package com.example.fefu_fitnes_compose.DataPakage.DataBase.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.fefu_fitnes_compose.DataPakage.DataBase.Models.UserEnterData

@Dao
interface FastEnterDao {
    @Query("SELECT * FROM userenterdata")
    fun getCrimes(): List<UserEnterData>
}