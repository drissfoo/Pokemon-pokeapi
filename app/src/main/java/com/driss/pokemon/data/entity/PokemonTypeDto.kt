package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.PokemonType
import com.driss.pokemon.domain.model.Type

data class PokemonTypeDto(
    val slot: Int,
    val type: TypeDto
)


fun List<PokemonTypeDto>.toModel(): List<PokemonType> {
    return map {
        PokemonType(
            slot = it.slot,
            type = Type(
                name = it.type.name,
                url = it.type.url
            )
        )
    }
}