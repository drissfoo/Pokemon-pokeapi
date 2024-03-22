package com.driss.pokemon.presentation.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.domain.usecase.pokemonlist.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val useCase: GetPokemonListUseCase
) : ViewModel() {

    private val _pagingData: MutableStateFlow<PagingData<Pokemon>> = MutableStateFlow(
        PagingData.empty()
    )
    val pagingData: StateFlow<PagingData<Pokemon>> = _pagingData.asStateFlow()

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            getPokemons()
        }
    }

    private suspend fun getPokemons() {
        useCase
            .execute()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _pagingData.value = it
            }
    }
}