package com.example.consumoapidungeondragons.api

class Repository {
    private val apiInterface = APIInterface.create()
    suspend fun getAllMonsters() = apiInterface.getMonsters()
    suspend fun getMonsterDetails(index: String) = apiInterface.getMonsterDetails(index)
}