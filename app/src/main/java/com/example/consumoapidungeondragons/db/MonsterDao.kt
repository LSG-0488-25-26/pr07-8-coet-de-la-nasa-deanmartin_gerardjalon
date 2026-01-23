package com.example.consumoapidungeondragons.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MonsterDao {
    @Query("SELECT * FROM monsters")
    fun getAllMonsters(): List<MonsterEntity>

    @Query("SELECT * FROM monsters WHERE `index` = :index")
    suspend fun getMonster(index: String): MonsterEntity?

    @Insert
    suspend fun insertMonster(monster: MonsterEntity)

    @Delete
    fun freeMonster(monster: MonsterEntity)

    @Query("UPDATE monsters SET is_killed = :isKilled WHERE `index` = :index")
    suspend fun updateMonster(index: String, isKilled: Boolean)

}