package com.example.consumoapidungeondragons.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.consumoapidungeondragons.api.APIInterface
import com.example.consumoapidungeondragons.viewmodel.MonstersViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsView(monsterIndex: String, navController: NavController, modifier: Modifier = Modifier) {
    val viewModel: MonstersViewModel = viewModel()
    val monsterDetails by viewModel.monsterDetails.observeAsState()
    val isLoading by viewModel.detailsLoading.observeAsState(false)
    val isKilled by viewModel.isKilled.observeAsState()



    LaunchedEffect(monsterIndex) {
        viewModel.getMonsterDetails(monsterIndex)
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val details = monsterDetails ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No se pudieron cargar los detalles.")
        }
        return
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                val imageUrl = "${APIInterface.BASE_URL}${details.image}"
                GlideImage(
                    model = imageUrl,
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )

                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = details.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${details.size} • ${details.type} • ${details.alignment}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        monsterDetails?.let {
                            viewModel.toggleKilled(
                                index = it.index,
                                name = it.name,
                                url = it.url
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isKilled == true)
                            Icons.Filled.Delete
                        else
                            Icons.Outlined.Delete,
                        contentDescription = null,
                        tint = if (isKilled == true) Color.Red else Color.Gray
                    )
                }
            }
        }

        item {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Overview", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)

                    KeyValue("Armor Class", details.armor_class.joinToString { "${it.type} (${it.value})" })
                    KeyValue("Hit Points", details.hit_points.toString())
                    KeyValue("Hit Dice", details.hit_dice)
                    KeyValue("Speed", details.speed.walk)

                    KeyValue("Challenge Rating", details.challenge_rating.toString())
                    KeyValue("XP", details.xp.toString())
                    KeyValue("Languages", details.languages)
                }
            }
        }

        item {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Stats", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)

                    KeyValue("STR", details.strength.toString())
                    KeyValue("DEX", details.dexterity.toString())
                    KeyValue("CON", details.constitution.toString())
                    KeyValue("INT", details.intelligence.toString())
                    KeyValue("WIS", details.wisdom.toString())
                    KeyValue("CHA", details.charisma.toString())
                }
            }
        }

        if (details.special_abilities.isNotEmpty()) {
            item {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Special Abilities", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)

                        details.special_abilities.forEach { ability ->
                            Text(
                                text = ability.name,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = ability.desc,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (details.special_abilities.last() != ability) {
                                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                            }
                        }
                    }
                }
            }
        }

        if (details.actions.isNotEmpty()) {
            item {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Actions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)

                        details.actions.forEach { action ->
                            Text(
                                text = action.name,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = action.desc,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (details.actions.last() != action) {
                                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun KeyValue(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.width(12.dp))
        Text(value, fontWeight = FontWeight.Medium)
    }
}
