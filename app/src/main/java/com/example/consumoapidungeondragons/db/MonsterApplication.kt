package com.example.consumoapidungeondragons.db

import android.app.Application
import androidx.room.Room

class MonsterApplication: Application() {

    companion object {
        lateinit var database: MonsterDatabase
    }

    override fun onCreate(){
        super.onCreate()
        database = Room.databaseBuilder(this, MonsterDatabase::class.java, "monsters").build()
    }
}