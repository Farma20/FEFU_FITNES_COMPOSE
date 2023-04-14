package com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.DataBase
import com.example.fefu_fitnes_compose.DataPakage.RoomDataBase.Models.User
import kotlinx.coroutines.launch

private const val DATABASE_NAME = "database"

class DataBaseRepository private constructor(context: Context):ViewModel() {

    val db = Room.databaseBuilder(
        context,
        DataBase::class.java, DATABASE_NAME
    ).build()

    val dao = db.dao

    fun addUserData(user: User) {
        viewModelScope.launch {
            dao.insertUser(user)
        }
    }

    fun getAllUserData(): LiveData<List<User>>{
        val result = MutableLiveData<List<User>>()
        viewModelScope.launch {
            result.postValue(dao.getAllUsers())
        }
       return result
    }

    companion object{
        private var INSTANCE:DataBaseRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = DataBaseRepository(context)
            }
        }

        fun get():DataBaseRepository{
            return INSTANCE?:throw IllegalStateException("Repository must be initialized")
        }

    }
}