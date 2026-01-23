package com.example.consumoapidungeondragons.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MonsterEntity::class], version = 2)
abstract class MonsterDatabase: RoomDatabase() {
    abstract fun monsterDao(): MonsterDao
}
