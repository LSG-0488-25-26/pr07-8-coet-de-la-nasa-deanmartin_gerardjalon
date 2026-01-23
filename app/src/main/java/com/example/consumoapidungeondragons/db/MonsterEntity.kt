package com.example.consumoapidungeondragons.db

import androidx.room.PrimaryKey

data class MonsterEntity(
    @PrimaryKey val index: String,
    val name: String
)
