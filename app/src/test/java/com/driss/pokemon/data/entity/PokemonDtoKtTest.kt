package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.util.FakePokemonRepository
import com.driss.pokemon.util.bulbasaur
import org.junit.Assert.assertTrue
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
        assertTrue(
            computedPokemon.name == expectedPokemon.name
                    && computedPokemon.id == expectedPokemon.id
                    && computedPokemon.frontSprite == expectedPokemon.frontSprite
                    && computedPokemon.pokemonTypes.size == expectedPokemon.pokemonTypes.size
                    && computedPokemon.stats.size == expectedPokemon.stats.size
                    && computedPokemon.weight == expectedPokemon.weight
                    && computedPokemon.height == expectedPokemon.height

        )
    }
}