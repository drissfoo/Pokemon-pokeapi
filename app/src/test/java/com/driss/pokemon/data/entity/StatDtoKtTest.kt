package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.Stat
import com.driss.pokemon.domain.model.StatType
import com.driss.pokemon.util.ivysaur
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StatDtoKtTest {


    @Test
    fun `check mapping from StatDto to Stat is precise`() {
        val entryPokemonStatsDto = ivysaur.stats
        val expectedPokemonStats = entryPokemonStatsDto.map {
            Stat(
                stat = StatType(
                    name = it.stat.name,
                    url = it.stat.url
                ),
                baseStat = it.baseStat,
                effort = it.effort
            )
        }
        val computedPokemonStats = entryPokemonStatsDto.toModel()
        assertThat(computedPokemonStats.size == expectedPokemonStats.size).isTrue()
        assertThat(computedPokemonStats[1].stat.name == expectedPokemonStats[1].stat.name).isTrue()
        assertThat(computedPokemonStats[1].stat.url == expectedPokemonStats[1].stat.url).isTrue()
        assertThat(computedPokemonStats[1].effort == expectedPokemonStats[1].effort).isTrue()
        assertThat(computedPokemonStats[1].baseStat == expectedPokemonStats[1].baseStat).isTrue()
    }
}