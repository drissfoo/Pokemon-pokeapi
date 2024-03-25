package com.driss.pokemon.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.driss.pokemon.data.entity.toModel
import com.driss.pokemon.data.source.PokemonApi
import com.driss.pokemon.domain.model.Pokemon

class PokemonListPagingSource(
    private val apiService: PokemonApi
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(PokemonListDataSourceFactory.PAGE_ITEMS_SIZE)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(PokemonListDataSourceFactory.PAGE_ITEMS_SIZE)
        }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Pokemon> {
        val offset = params.key ?: 0
        val limit = params.loadSize
        val response = apiService.getPokemonList(offset = offset, limit = limit)
        val data = response.body()
        return if (!response.isSuccessful) {
            LoadResult.Error(Exception("Request error"))
        } else if (data != null) {
            val list: List<Pokemon> = data.results.mapNotNull {
                apiService.getPokemonDetail(it.name).body()?.toModel()
            }
            LoadResult.Page(
                data = list,
                prevKey = if (offset < limit) null else (offset - limit),
                nextKey = if (offset >= data.count) null else (offset + limit)
            )
        } else {
            LoadResult.Error(Exception("No Response"))
        }
    }

}
