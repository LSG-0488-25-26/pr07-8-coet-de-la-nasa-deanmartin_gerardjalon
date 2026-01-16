package com.example.consumoapidungeondragons.api

class Repository {
    val apiInterface = com.example.retrofitapp.api.APIInterface.Companion.create()
    suspend fun getAllMonsters() = apiInterface.getMonsters()
}