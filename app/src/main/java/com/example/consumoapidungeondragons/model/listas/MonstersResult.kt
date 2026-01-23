package com.example.consumoapidungeondragons.model.listas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "monsters")
data class MonstersResult(
    @PrimaryKey val index: String,
    val name: String,
    val url: String,
    @Ignore val isKilled: Boolean = false
)
