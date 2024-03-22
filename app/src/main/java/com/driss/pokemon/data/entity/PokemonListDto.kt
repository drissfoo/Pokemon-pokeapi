package com.driss.pokemon.data.entity

data class PokemonListDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonRefDto>
) {
    companion object
}