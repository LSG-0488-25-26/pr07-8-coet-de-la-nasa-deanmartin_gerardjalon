package com.example.consumoapidungeondragons.model.listas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monsters")
data class MonstersResult(
    @PrimaryKey var index: String,
    var name: String,
    var url: String,
    @ColumnInfo(defaultValue = "0")
    var isKilled: Boolean = false
)
