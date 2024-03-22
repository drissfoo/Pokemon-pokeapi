package com.driss.pokemon.domain.usecase.pokemonlist

import androidx.paging.PagingData
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonListUseCase @Inject constructor(private val factory :PokemonListDataSourceFactory) {

    fun execute(): Flow<PagingData<Pokemon>> {
        return factory.getPokemonListPager()
    }
}