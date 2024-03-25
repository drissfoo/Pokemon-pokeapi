package com.driss.pokemon.data.repository

import androidx.paging.PagingData
import com.driss.pokemon.data.entity.toModel
import com.driss.pokemon.data.source.PokemonApi
import com.driss.pokemon.data.source.paging.PokemonListDataSourceFactory
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.domain.repository.PokemonRepository
import com.driss.pokemon.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokemonApi,
    private val factory: PokemonListDataSourceFactory
) : PokemonRepository {
    override fun getPokemonList(): Flow<PagingData<Pokemon>> =
        factory.getPokemonListPager()

    override fun getPokemonDetail(name: String): Flow<Result<Pokemon>> = flow {
        emit(Result.loading())
        val response = apiService.getPokemonDetail(name)
        if (response.isSuccessful)
            emit(Result.success(response.body()?.toModel()))
        else
            emit(Result.error(message = "Failed to retrieve pokemon $name"))
    }

}