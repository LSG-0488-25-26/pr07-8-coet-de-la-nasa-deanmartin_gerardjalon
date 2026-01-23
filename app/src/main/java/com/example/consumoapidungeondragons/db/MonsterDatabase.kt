package com.example.consumoapidungeondragons.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.consumoapidungeondragons.model.listas.MonstersResult

@Database(entities = [MonstersResult::class], version = 2)
abstract class MonsterDatabase: RoomDatabase() {
    abstract fun monsterDao(): MonsterDao
}
