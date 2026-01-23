package com.example.consumoapidungeondragons.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.consumoapidungeondragons.model.listas.MonstersResult

@Dao
interface MonsterDao {
    @Query("SELECT * FROM monsters")
    fun getAllMonsters(): List<MonstersResult>

    @Query("SELECT * FROM monsters WHERE `index` = :index")
    suspend fun getMonster(index: String): MonstersResult?

    @Insert
    suspend fun killMonster(monster: MonstersResult)

    @Delete
    fun freeMonster(monster: MonstersResult)

    @Query("UPDATE monsters SET is_killed = :isKilled WHERE `index` = :index")
    suspend fun updateMonster(index: String, isKilled: Boolean)

}