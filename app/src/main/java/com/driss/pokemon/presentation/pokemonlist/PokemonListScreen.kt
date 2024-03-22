package com.driss.pokemon.presentation.pokemonlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.driss.pokemon.presentation.common.ErrorStateComponent
import com.driss.pokemon.ui.theme.Sizes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val semiTransparentSurface = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
    val pagingData = viewModel.pagingData.collectAsLazyPagingItems()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Pokemon List")
        },
            actions = {
                IconButton(onClick = {
                    viewModel.getPokemonList()
                }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                }
            })
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(modifier = Modifier.weight(weight = 1f, fill = true)) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(count = 2),
                        contentPadding = PaddingValues(Sizes.M)
                    ) {
                        items(pagingData.itemCount) { index ->

                            pagingData[index]?.let { pokemon ->
                                PokemonCell(
                                    entry = pokemon,
                                    modifier = Modifier
                                        .padding(Sizes.XXS)
                                )
                            }
                        }

                    }
                    pagingData.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(semiTransparentSurface)
                                    .align(Alignment.Center),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }

                            loadState.refresh is LoadState.Error -> Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(semiTransparentSurface)
                                    .align(Alignment.Center),
                                contentAlignment = Alignment.Center
                            ) {
                                ErrorStateComponent()
                            }

                            loadState.append is LoadState.Loading -> Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(semiTransparentSurface)
                                    .align(Alignment.BottomCenter),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }

                            loadState.append is LoadState.Error -> Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(semiTransparentSurface)
                                    .align(Alignment.BottomCenter),
                                contentAlignment = Alignment.Center
                            ) {
                                ErrorStateComponent()
                            }
                        }
                    }
                }
            }
        }
    }
}