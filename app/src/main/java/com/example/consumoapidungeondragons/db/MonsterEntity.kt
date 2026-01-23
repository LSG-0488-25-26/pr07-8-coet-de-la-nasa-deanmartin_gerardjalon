package com.example.consumoapidungeondragons.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monsters")
data class MonsterEntity(
    @PrimaryKey val index: String,
    val name: String,
    @ColumnInfo(name = "is_killed") var isKilled: Boolean = false
)
