package com.driss.pokemon.domain.usecase.list

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.driss.pokemon.data.entity.toModel
import com.driss.pokemon.data.source.paging.PokemonListDataSourceFactory
import com.driss.pokemon.data.source.paging.PokemonListPagingSource
import com.driss.pokemon.util.FakeApiService
import com.driss.pokemon.util.listPokemonDto
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PokemonPagingDatasourceTest {

    private val fakeApi = FakeApiService()

    @Test
    fun `load Returns Page compliant with the api faked data`() = runTest {
        val pagingSource = PokemonListPagingSource(fakeApi)
        val pager = TestPager(
            PagingConfig(
                pageSize = PokemonListDataSourceFactory.PAGE_ITEMS_SIZE,
                enablePlaceholders = false
            ), pagingSource
        )
        val result = pager.refresh() as PagingSource.LoadResult.Page
        assertThat(result.data)
            .containsExactlyElementsIn(listPokemonDto.map { it.toModel() })
            .inOrder()
    }
}

