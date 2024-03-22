package com.driss.pokemon.domain.model

data class Pokemon(
    val height: Int,
    val id: Int,
    val name: String,
    val frontSprite: String,
    val stats: List<Stat>,
    val pokemonTypes: List<PokemonType>,
    val weight: Int
)