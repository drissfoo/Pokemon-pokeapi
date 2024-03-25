package com.driss.pokemon.domain.usecase.list

import androidx.paging.PagingData
import com.driss.pokemon.data.source.paging.PokemonListDataSourceFactory
import com.driss.pokemon.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val factory: PokemonListDataSourceFactory) {

    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return factory.getPokemonListPager()
    }
}