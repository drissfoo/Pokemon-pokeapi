package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.PokemonType
import com.driss.pokemon.domain.model.Stat
import com.driss.pokemon.domain.model.StatType
import com.driss.pokemon.domain.model.Type
import com.driss.pokemon.util.FakePokemonRepository
import org.junit.Assert.*
import org.junit.Test

class StatDtoKtTest{

    private val repository: FakePokemonRepository = FakePokemonRepository()

    @Test
    fun `check mapping from StatDto to Stat is precise`() {
        val entryPokemonStatsDto = repository.ivysaur.stats
        val expectedPokemonStats = entryPokemonStatsDto.map {
            Stat(
                stat = StatType(
                    name = it.stat.name,
                    url = it.stat.url
                ),
                baseStat = it.base_stat,
                effort = it.effort
            )
        }
        val computedPokemonStats = entryPokemonStatsDto.toModel()
        assertTrue(
            computedPokemonStats.size == expectedPokemonStats.size
                    && computedPokemonStats[1].stat.name == expectedPokemonStats[1].stat.name
                    && computedPokemonStats[1].stat.url == expectedPokemonStats[1].stat.url
                    && computedPokemonStats[1].effort == expectedPokemonStats[1].effort
                    && computedPokemonStats[1].baseStat == expectedPokemonStats[1].baseStat

        )
    }
}