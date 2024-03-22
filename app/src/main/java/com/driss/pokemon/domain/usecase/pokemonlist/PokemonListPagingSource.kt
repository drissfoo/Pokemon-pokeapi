package com.driss.pokemon.domain.usecase.pokemonlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.driss.pokemon.data.entity.toModel
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.domain.repository.PokemonRepository

class PokemonListPagingSource(
    private val repository: PokemonRepository
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(PokemonListDataSourceFactory.PAGE_ITEMS_SIZE)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(PokemonListDataSourceFactory.PAGE_ITEMS_SIZE)
        }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Pokemon> = try {
        val offset = params.key ?: 0
        val limit = params.loadSize
        val response = repository.getPokemonList(offset = offset, limit = limit)
        val data = response.body()
        if (!response.isSuccessful) {
            LoadResult.Error(Exception("Request error"))
        } else if (data != null) {
            val list: List<Pokemon> = data.results.mapNotNull {
                repository.getPokemonDetail(it.name).body()?.toModel()
            }
            LoadResult.Page(
                data = list,
                prevKey = if (offset < limit) null else (offset - limit),
                nextKey = if (offset >= data.count) null else (offset + limit)
            )
        } else {
            LoadResult.Error(Exception("No Response"))
        }
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

}
