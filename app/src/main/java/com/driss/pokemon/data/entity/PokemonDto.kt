package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.Pokemon

data class PokemonDto(
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: SpritesDto,
    val stats: List<StatDto>,
    val types: List<PokemonTypeDto>,
    val weight: Int
)



internal fun PokemonDto.toModel() : Pokemon {
    return Pokemon(
        height = height,
        id = id,
        name = name,
        frontSprite = sprites.frontDefault,
        stats = stats.toModel(),
        pokemonTypes = types.toModel(),
        weight = weight
    )
}
