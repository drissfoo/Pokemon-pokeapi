package com.driss.pokemon.domain.repository

import com.driss.pokemon.data.entity.PokemonDto
import com.driss.pokemon.data.entity.PokemonListDto
import retrofit2.Response

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonListDto>

    suspend fun getPokemonDetail(name: String): Response<PokemonDto>
}