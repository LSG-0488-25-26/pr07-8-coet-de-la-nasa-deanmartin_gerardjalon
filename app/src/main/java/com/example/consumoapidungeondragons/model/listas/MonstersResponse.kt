package com.example.consumoapidungeondragons.model.listas

import com.example.consumoapidungeondragons.model.listas.MonstersResult

data class MonstersResponse(
    val count: Int,
    val results: List<MonstersResult>
)