package com.example.consumoapidungeondragons.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.consumoapidungeondragons.Routes

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.ListView.route,
        modifier = modifier
    ) {
        composable(Routes.ListView.route) {
            ListView(modifier = modifier, navController = navController)
        }

        composable(Routes.DetailsView.route) { backStackEntry ->
            val monsterIndex = backStackEntry.arguments?.getString("monsterId")

            if (monsterIndex != null) {
                DetailsView(monsterIndex = monsterIndex)
            } else {
                navController.navigateUp()
            }
        }
    }
}