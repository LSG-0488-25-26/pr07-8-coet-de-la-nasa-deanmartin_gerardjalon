package com.example.consumoapidungeondragons.model.details

data class Action(
    val actions: List<Any>,
    val attack_bonus: Int,
    val damage: List<Damage>,
    val desc: String,
    val name: String
)