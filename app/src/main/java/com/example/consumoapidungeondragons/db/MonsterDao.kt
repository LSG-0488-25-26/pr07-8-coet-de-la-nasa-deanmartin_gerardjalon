package com.example.consumoapidungeondragons.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.consumoapidungeondragons.model.listas.MonstersResult

@Dao
interface MonsterDao {
    @Query("SELECT * FROM monsters")
    suspend fun getAllMonsters(): List<MonstersResult>

    @Query("SELECT * FROM monsters WHERE `index` = :index")
    suspend fun getMonster(index: String): MonstersResult?

    @Query("SELECT isKilled FROM monsters WHERE `index` = :index")
    suspend fun isKilled(index: String): Boolean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(monster: MonstersResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun killMonster(monster: MonstersResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun freeMonster(monster: MonstersResult)
}
