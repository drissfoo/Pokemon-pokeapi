package com.driss.pokemon.domain.usecase.detail

import com.driss.pokemon.util.FakePokemonRepository
import com.driss.pokemon.util.IResult
import com.driss.pokemon.util.listPokemonDto
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonDetailUseCaseTest {
    private lateinit var fakeRepo: FakePokemonRepository
    lateinit var detailUseCase: GetPokemonDetailUseCase

    @Before
    fun setUp() {
        fakeRepo = FakePokemonRepository()
        detailUseCase = GetPokemonDetailUseCase(fakeRepo)
    }

    @Test
    fun `get pokemon detail by name expect success`() = runBlocking {
        val expectedResult = listPokemonDto[0]
        detailUseCase(expectedResult.name).collect {
            when (it.status) {
                IResult.Status.SUCCESS -> assertThat(it.data?.name).isEqualTo(expectedResult.name)
                IResult.Status.ERROR -> assertThat(false).isTrue()
                IResult.Status.LOADING -> assertThat(true).isTrue()
            }
        }
    }


    @Test
    fun `get pokemon detail by name expect error`() = runBlocking {
        val expectedResult = "not_there"
        detailUseCase(expectedResult).collect {
            when (it.status) {
                IResult.Status.SUCCESS -> assertThat(false).isTrue()
                IResult.Status.ERROR -> assertThat(true).isTrue()
                IResult.Status.LOADING -> assertThat(true).isTrue()
            }
        }
    }
}