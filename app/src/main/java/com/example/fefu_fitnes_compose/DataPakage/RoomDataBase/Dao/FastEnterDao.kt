package com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Models.User

@Dao
interface FastEnterDao {
    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}