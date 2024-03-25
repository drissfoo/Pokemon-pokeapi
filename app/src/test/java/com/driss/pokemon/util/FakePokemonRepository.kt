package com.driss.pokemon.util

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.driss.pokemon.data.entity.toModel
import com.driss.pokemon.data.source.paging.PokemonListDataSourceFactory
import com.driss.pokemon.data.source.paging.PokemonListPagingSource
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonRepository : PokemonRepository {
    private val apiService = FakeApiService()

    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = PokemonListDataSourceFactory.PAGE_ITEMS_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonListPagingSource(apiService) }
        ).flow
    }

    override fun getPokemonDetail(name: String): Flow<Result<Pokemon>> {
        return flow {
            emit(Result.loading())
            val pokemon = listPokemonDto.firstOrNull {
                name == it.name
            }
            if (pokemon != null) {
                emit(Result.success(pokemon.toModel()))
            } else emit(Result.error("Error data"))
        }

    }
}