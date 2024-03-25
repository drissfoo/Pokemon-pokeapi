package com.driss.pokemon.util

import com.driss.pokemon.data.entity.PokemonDto
import com.driss.pokemon.data.entity.PokemonListDto
import com.driss.pokemon.data.source.PokemonApi
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import retrofit2.Response

class FakeApiService : PokemonApi {

    override suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonListDto> {
        return Response.success(
            PokemonListDto(
                count = 3,
                next = "",
                previous = "",
                results = listPokemonRef
            )
        )
    }

    override suspend fun getPokemonDetail(name: String): Response<PokemonDto> {
        val pokemon = listPokemonDto.firstOrNull {
            name == it.name
        }
        return if (pokemon != null) {
            Response.success(pokemon)
        } else Response.error(400, object : ResponseBody() {
            override fun contentLength(): Long = 10L

            override fun contentType(): MediaType? = null

            override fun source(): BufferedSource = TODO("Not yet implemented")

        })
    }

}