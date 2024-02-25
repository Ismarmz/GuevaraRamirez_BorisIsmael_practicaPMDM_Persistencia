package com.example.guevararamirez_borisismael_practicapmdm_persistencia.modelDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class  Tasks(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "tittle")
    val tittle: String,
    @ColumnInfo(name="description")
    val description: String?,
    @ColumnInfo(name="id_module")
    val idModule: Int,

    var isChecked: Boolean = false
)
