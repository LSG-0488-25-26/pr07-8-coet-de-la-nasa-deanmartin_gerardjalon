package com.example.consumoapidungeondragons.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.consumoapidungeondragons.viewmodel.MonstersViewModel

@Composable
fun ListView(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: MonstersViewModel = viewModel()
    val monsters = viewModel.monsters.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getMonsters()
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(monsters.value) { monster ->
            CharacterItem(monster = monster, navController = navController)
        }
    }
}