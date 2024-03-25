package com.driss.pokemon.domain.usecase.detail

import com.driss.pokemon.util.FakePokemonRepository
import com.driss.pokemon.util.IResult
import com.driss.pokemon.util.listPokemonDto
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
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
                IResult.Status.SUCCESS -> assertTrue(it.data?.name == expectedResult.name)
                IResult.Status.ERROR -> assertTrue(false)
                IResult.Status.LOADING -> assertTrue(true)
            }
        }
    }


    @Test
    fun `get pokemon detail by name expect error`() = runBlocking {
        val expectedResult = "not_there"
        detailUseCase(expectedResult).collect {
            when (it.status) {
                IResult.Status.SUCCESS -> assertTrue(false)
                IResult.Status.ERROR -> assertTrue(true)
                IResult.Status.LOADING -> assertTrue(true)
            }
        }
    }
}