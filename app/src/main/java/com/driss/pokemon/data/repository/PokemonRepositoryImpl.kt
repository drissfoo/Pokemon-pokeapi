package com.driss.pokemon.data.repository

import com.driss.pokemon.data.entity.PokemonDto
import com.driss.pokemon.data.entity.PokemonListDto
import com.driss.pokemon.data.source.PokemonApi
import com.driss.pokemon.domain.repository.PokemonRepository
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val apiService: PokemonApi) :
    PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonListDto> =
        apiService.getPokemonList(limit, offset = offset)

    override suspend fun getPokemonDetail(name: String): Response<PokemonDto> =
        apiService.getPokemonDetail(name)

}