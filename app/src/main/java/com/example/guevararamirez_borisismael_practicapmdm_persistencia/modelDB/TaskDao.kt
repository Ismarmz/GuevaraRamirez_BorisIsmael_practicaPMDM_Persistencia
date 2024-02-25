package com.example.guevararamirez_borisismael_practicapmdm_persistencia.modelDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(tasks: Tasks)
    @Delete
    suspend fun deleteTask(tasks: Tasks)
    @Update
    suspend fun updateTask(tasks: Tasks)

    @Query("select * from task where id_module = :idmodule order by tittle asc")
    fun getAllTaskFromModule(idmodule: Int): LiveData<List<Tasks>>
}