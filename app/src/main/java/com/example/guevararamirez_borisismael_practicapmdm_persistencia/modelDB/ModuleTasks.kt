package com.example.guevararamirez_borisismael_practicapmdm_persistencia.modelDB

import androidx.room.Embedded
import androidx.room.Relation

data class ModuleTasks (
    @Embedded val module: Module,
    @Relation(
        parentColumn = "id",
        entityColumn = "id_module"
    )
    val tasks: List<Tasks>
)
