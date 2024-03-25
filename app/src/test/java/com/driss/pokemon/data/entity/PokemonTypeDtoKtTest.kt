package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.PokemonType
import com.driss.pokemon.domain.model.Type
import com.driss.pokemon.util.bulbasaur
import com.google.common.truth.Truth.assertThat

import org.junit.Test

class PokemonTypeDtoKtTest {


    @Test
    fun `check mapping from PokemonTypesDto to PokemonType is precise`() {
        val entryPokemonTypesDto = bulbasaur.types
        val expectedPokemon = entryPokemonTypesDto.map {
            PokemonType(
                type = Type(
                    name = it.type.name,
                    url = it.type.url
                ),
                slot = it.slot
            )
        }
        val computedPokemonTypes = entryPokemonTypesDto.toModel()
        assertThat(computedPokemonTypes.size == expectedPokemon.size).isTrue()
        assertThat(computedPokemonTypes[0].type.name == expectedPokemon[0].type.name).isTrue()
        assertThat(computedPokemonTypes[0].type.url == expectedPokemon[0].type.url).isTrue()
        assertThat(computedPokemonTypes[0].slot == expectedPokemon[0].slot).isTrue()
    }
}