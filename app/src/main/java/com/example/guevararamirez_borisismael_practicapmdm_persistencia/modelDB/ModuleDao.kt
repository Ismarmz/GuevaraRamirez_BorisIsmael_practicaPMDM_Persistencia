package com.example.guevararamirez_borisismael_practicapmdm_persistencia.modelDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ModuleDao {
    @Insert
    suspend fun insertAll(module: List<Module>)
    @Query("select * from module")
    fun getAllModule(): LiveData<List<Module>>
}