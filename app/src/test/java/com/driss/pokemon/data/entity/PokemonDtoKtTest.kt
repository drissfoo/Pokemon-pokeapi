package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.util.bulbasaur
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PokemonDtoKtTest {

    @Test
    fun `check mapping from PokemonDto to Pokemon is precise`() {
        val entryPokemonDto = bulbasaur
        val expectedPokemon = Pokemon(
            name = entryPokemonDto.name,
            stats = entryPokemonDto.stats.toModel(),
            weight = entryPokemonDto.weight,
            height = entryPokemonDto.height,
            frontSprite = entryPokemonDto.sprites.frontDefault,
            pokemonTypes = entryPokemonDto.types.toModel(),
            id = entryPokemonDto.id
        )
        val computedPokemon = entryPokemonDto.toModel()
        assertThat(computedPokemon.name == expectedPokemon.name).isTrue()
        assertThat(computedPokemon.id == expectedPokemon.id).isTrue()
        assertThat(computedPokemon.frontSprite == expectedPokemon.frontSprite).isTrue()
        assertThat(computedPokemon.pokemonTypes.size == expectedPokemon.pokemonTypes.size).isTrue()
        assertThat(computedPokemon.stats.size == expectedPokemon.stats.size).isTrue()
        assertThat(computedPokemon.weight == expectedPokemon.weight).isTrue()
        assertThat(computedPokemon.height == expectedPokemon.height).isTrue()
    }
}