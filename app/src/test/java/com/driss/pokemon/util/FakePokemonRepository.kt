package com.driss.pokemon.util

import com.driss.pokemon.data.entity.PokemonDto
import com.driss.pokemon.data.entity.PokemonListDto
import com.driss.pokemon.data.entity.PokemonRefDto
import com.driss.pokemon.data.entity.PokemonTypeDto
import com.driss.pokemon.data.entity.SpritesDto
import com.driss.pokemon.data.entity.StatDto
import com.driss.pokemon.data.entity.StatTypeDto
import com.driss.pokemon.data.entity.TypeDto
import com.driss.pokemon.domain.repository.PokemonRepository
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import retrofit2.Response

class FakePokemonRepository : PokemonRepository {
    val listPokemonRef = listOf(
        PokemonRefDto(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        ),
        PokemonRefDto(
            name = "ivysaur",
            url = "https://pokeapi.co/api/v2/pokemon/2/"
        ),
        PokemonRefDto(
            name = "venusaur",
            url = "https://pokeapi.co/api/v2/pokemon/3/"
        ),
    )
    val bulbasaur = PokemonDto(
        height = 0,
        id = 1,
        name = "bulbasaur",
        sprites = SpritesDto(""),
        stats = listOf(
            StatDto(
                baseStat = 23,
                effort = 0,
                stat = StatTypeDto(
                    name = "Attack",
                    url = ""
                )
            ),
        ),
        weight = 0,
        types = listOf(
            PokemonTypeDto(
                slot = 1,
                type = TypeDto(
                    name = "grass",
                    url = ""
                )
            ),
            PokemonTypeDto(
                slot = 2,
                type = TypeDto(
                    name = "water",
                    url = ""
                )
            ),
        )
    )
    val ivysaur = PokemonDto(
        height = 0,
        id = 2,
        name = "ivysaur",
        sprites = SpritesDto(""),
        stats = listOf(
            StatDto(
                baseStat = 66,
                effort = 0,
                stat = StatTypeDto(
                    name = "hp",
                    url = ""
                )
            ),
            StatDto(
                baseStat = 78,
                effort = 0,
                stat = StatTypeDto(
                    name = "Speed",
                    url = ""
                )
            ),
        ),
        weight = 0,
        types = listOf(
            PokemonTypeDto(
                slot = 1,
                type = TypeDto(
                    name = "airy",
                    url = ""
                )
            ),
            PokemonTypeDto(
                slot = 2,
                type = TypeDto(
                    name = "electric",
                    url = ""
                )
            ),
        )
    )
    val venusaur = PokemonDto(
        height = 0,
        id = 3,
        name = "venusaur",
        sprites = SpritesDto(""),
        stats = listOf(
            StatDto(
                baseStat = 23,
                effort = 0,
                stat = StatTypeDto(
                    name = "Attack",
                    url = ""
                )
            ),
            StatDto(
                baseStat = 89,
                effort = 0,
                stat = StatTypeDto(
                    name = "hp",
                    url = ""
                )
            ),
        ),
        weight = 0,
        types = listOf(
            PokemonTypeDto(
                slot = 2,
                type = TypeDto(
                    name = "normal",
                    url = ""
                )
            ),
        )
    )
    val listPokemonDto: List<PokemonDto> = listOf(
        bulbasaur,
        ivysaur,
        venusaur,
    )

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