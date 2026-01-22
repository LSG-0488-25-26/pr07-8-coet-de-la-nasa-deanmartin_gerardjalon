package com.example.consumoapidungeondragons.api

import com.example.consumoapidungeondragons.model.details.MonsterDetails
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.consumoapidungeondragons.model.listas.MonstersResponse

interface APIInterface {
    @GET("api/monsters")
    suspend fun getMonsters(): Response<MonstersResponse>
    @GET("api/monsters/{index}")
    suspend fun getMonsterDetails(@Path("index") index: String): Response<MonsterDetails>
    companion object {
        const val BASE_URL = "https://www.dnd5eapi.co"
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}