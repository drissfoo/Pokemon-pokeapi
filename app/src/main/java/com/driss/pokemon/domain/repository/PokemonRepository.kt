package com.driss.pokemon.domain.repository

import androidx.paging.PagingData
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.util.Result
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>

    fun getPokemonDetail(name: String): Flow<Result<Pokemon>>
}