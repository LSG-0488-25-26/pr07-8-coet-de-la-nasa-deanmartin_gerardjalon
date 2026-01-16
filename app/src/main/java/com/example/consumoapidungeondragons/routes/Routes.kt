package com.example.consumoapidungeondragons.nav

sealed class Routes(val route: String) {
    object ListView : Routes("ListView")
    object DetailsView : Routes("DetailsScreen/{monsterId}") {
        fun createRoute(monsterIndex: String) = "DetailsScreen/$monsterIndex"
    }
}