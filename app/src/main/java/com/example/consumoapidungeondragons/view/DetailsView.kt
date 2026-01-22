package com.example.consumoapidungeondragons.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.consumoapidungeondragons.viewmodel.MonstersViewModel

@Composable
fun DetailsView(monsterIndex: String, modifier: Modifier = Modifier) {
    val viewModel: MonstersViewModel = viewModel()
    val monsterDetails by viewModel.monsterDetails.observeAsState()
    val isLoading by viewModel.detailsLoading.observeAsState(false)

    LaunchedEffect(monsterIndex) {
        viewModel.getMonsterDetails(monsterIndex)
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        monsterDetails?.let { details ->
            LazyColumn(modifier = modifier.padding(16.dp)) {
                item {
                    Column {
                        Text(text = "Name: ${details.name}")
                        Text(text = "Size: ${details.size}")
                        Text(text = "Type: ${details.type}")
                        Text(text = "Alignment: ${details.alignment}")
                        Text(text = "Armor Class: ${details.armor_class.joinToString { "${it.type} (${it.value})" }}")
                        Text(text = "Hit Points: ${details.hit_points}")
                        Text(text = "Hit Dice: ${details.hit_dice}")
                        Text(text = "Speed: ${details.speed.walk}")
                        Text(text = "Strength: ${details.strength}")
                        Text(text = "Dexterity: ${details.dexterity}")
                        Text(text = "Constitution: ${details.constitution}")
                        Text(text = "Intelligence: ${details.intelligence}")
                        Text(text = "Wisdom: ${details.wisdom}")
                        Text(text = "Charisma: ${details.charisma}")
                        Text(text = "Proficiency Bonus: ${details.proficiency_bonus}")
                        Text(text = "Challenge Rating: ${details.challenge_rating}")
                        Text(text = "XP: ${details.xp}")
                        Text(text = "Senses: ${details.senses}")
                        Text(text = "Languages: ${details.languages}")

                        if (details.special_abilities.isNotEmpty()) {
                            Text(text = "\nSpecial Abilities:")
                            details.special_abilities.forEach { ability ->
                                Text(text = "- ${ability.name}: ${ability.desc}")
                            }
                        }

                        if (details.actions.isNotEmpty()) {
                            Text(text = "\nActions:")
                            details.actions.forEach { action ->
                                Text(text = "- ${action.name}: ${action.desc}")
                            }
                        }
                    }
                }
            }
        }
    }
}