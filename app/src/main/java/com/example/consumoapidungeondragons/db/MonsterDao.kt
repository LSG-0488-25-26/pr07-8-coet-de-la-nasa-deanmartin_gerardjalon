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

    @Query("SELECT EXISTS(SELECT 1 FROM monsters WHERE `index` = :index)")
    suspend fun isKilled(index: String): Boolean

    @Insert
    suspend fun killMonster(monster: MonstersResult)

    @Delete
    suspend fun freeMonster(index: String)

    //Como podemos hacer para updatear o buscar un monster si tenemos un ignore en la clase para
    //no importar todas las filas de la api, o ignore o column info
    //ignore -> No podemos updatear ni buscar por filtro
    //column info -> Podemos pero hay que insertar todas las filas


}