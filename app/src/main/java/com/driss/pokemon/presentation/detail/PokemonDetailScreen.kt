package com.driss.pokemon.presentation.detail

import androidx.annotation.OpenForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.driss.pokemon.LocalNavController
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.presentation.common.ErrorStateComponent
import com.driss.pokemon.presentation.common.PokemonStatsComponent
import com.driss.pokemon.presentation.common.PokemonTypeComponent
import com.driss.pokemon.ui.theme.Sizes
import com.driss.pokemon.util.IResult
import com.driss.pokemon.util.extensions.capitalizeFirst
import com.skydoves.landscapist.coil.CoilImage
import okhttp3.internal.toImmutableList

@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    val pokemonData by viewModel.pokemon.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.getPokemon(pokemonName)
    }

    when (pokemonData.status) {
        IResult.Status.SUCCESS -> PokemonDetail(pokemonData.data, modifier)
        IResult.Status.ERROR -> ErrorStateComponent(modifier = modifier)
        IResult.Status.LOADING -> CircularProgressIndicator(modifier = modifier)
    }
}

@OpenForTesting
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetail(
    pokemonDetails: Pokemon?,
    modifier: Modifier = Modifier,
) {
    val navController = LocalNavController.current

    pokemonDetails?.let { details ->
        Scaffold(
            modifier = modifier,
            topBar = {
            TopAppBar(title = { Text(text = details.name.capitalizeFirst()) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                })
        }) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoilImage(
                    modifier = Modifier.size(120.dp),
                    imageModel = {
                        details.frontSprite
                    },
                )
                Text(
                    modifier = Modifier
                        .padding(top = Sizes.XXS)
                        .fillMaxWidth(),
                    text = details.name.capitalizeFirst(),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(Sizes.M))
                PokemonTypeComponent(pokemonTypeSlots = details.pokemonTypes.toImmutableList())
                Spacer(modifier = Modifier.height(Sizes.M))
                PokemonStatsComponent(stats = details.stats)
            }
        }
    }
}

