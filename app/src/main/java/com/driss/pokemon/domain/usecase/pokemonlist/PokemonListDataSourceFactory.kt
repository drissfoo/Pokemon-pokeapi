package com.driss.pokemon.domain.usecase.pokemonlist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.driss.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonListDataSourceFactory @Inject constructor(
    private val repository: PokemonRepository
) {
    companion object {
        const val PAGE_ITEMS_SIZE = 20
    }

    fun getPokemonListPager() = Pager(
        config = PagingConfig(
            pageSize = PAGE_ITEMS_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PokemonListPagingSource(repository) }
    ).flow
}