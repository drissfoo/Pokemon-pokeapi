package com.driss.pokemon.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.driss.pokemon.util.Result
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.domain.usecase.pokemondetail.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val useCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _pokemon: MutableStateFlow<Result<Pokemon?>> = MutableStateFlow(Result.loading())
    val pokemon: StateFlow<Result<Pokemon?>> = _pokemon.asStateFlow()

    fun getPokemon(name: String) {
        viewModelScope.launch {
            useCase.execute(name = name).collect{
                _pokemon.value = it
            }
        }
    }
}