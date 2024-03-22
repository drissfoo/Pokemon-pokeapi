package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.PokemonType
import com.driss.pokemon.domain.model.Type
import com.driss.pokemon.util.FakePokemonRepository
import org.junit.Assert.*

import org.junit.Test

class PokemonTypeDtoKtTest {

    private val repository: FakePokemonRepository = FakePokemonRepository()

    @Test
    fun `check mapping from PokemonTypesDto to PokemonType is precise`() {
        val entryPokemonTypesDto = repository.bulbasaur.types
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
        assertTrue(
            computedPokemonTypes.size == expectedPokemon.size
                    && computedPokemonTypes[0].type.name == expectedPokemon[0].type.name
                    && computedPokemonTypes[0].type.url == expectedPokemon[0].type.url
                    && computedPokemonTypes[0].slot == expectedPokemon[0].slot

        )
    }
}