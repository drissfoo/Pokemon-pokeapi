package com.driss.pokemon.data.source.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.driss.pokemon.data.source.PokemonApi
import javax.inject.Inject

class PokemonListDataSourceFactory @Inject constructor(
    private val apiService: PokemonApi
) {
    companion object {
        const val PAGE_ITEMS_SIZE = 20
    }

    fun getPokemonListPager() = Pager(
        config = PagingConfig(
            pageSize = PAGE_ITEMS_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PokemonListPagingSource(apiService) }
    ).flow
}