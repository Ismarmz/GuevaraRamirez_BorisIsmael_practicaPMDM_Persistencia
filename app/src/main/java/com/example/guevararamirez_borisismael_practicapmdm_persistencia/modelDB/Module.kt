package com.example.guevararamirez_borisismael_practicapmdm_persistencia.modelDB

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "module")
data class Module(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @NonNull @ColumnInfo(name = "module_name")
    val moduleName: String
)