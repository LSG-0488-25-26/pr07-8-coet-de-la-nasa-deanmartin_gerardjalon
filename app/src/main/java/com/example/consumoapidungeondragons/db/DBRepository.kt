package com.example.consumoapidungeondragons.db

import com.example.consumoapidungeondragons.model.listas.MonstersResult

class DBRepository {
    val daoInterface = MonsterApplication.database.monsterDao()

    suspend fun getAllMonsters() = daoInterface.getAllMonsters()
    suspend fun getMonster(index: String) = daoInterface.getMonster(index)
    suspend fun isKilled(index: String) = daoInterface.isKilled(index)
    suspend fun insert(monster: MonstersResult) = daoInterface.insert(monster)
}
