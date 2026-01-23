package com.example.consumoapidungeondragons.db

import com.example.consumoapidungeondragons.model.listas.MonstersResult

class DBRepository {
    val daoInterface = MonsterApplication.database.monsterDao()

    suspend fun getAllMonsters() = daoInterface.getAllMonsters()
    suspend fun getMonster(index: String) = daoInterface.getMonster(index)
    suspend fun killMonster(monster: MonstersResult) = daoInterface.killMonster(monster)
    suspend fun freeMonster(monster: MonstersResult) = daoInterface.freeMonster(monster)
    suspend fun updateMonster(index: String, isKilled: Boolean) = daoInterface.updateMonster(index, isKilled)

}