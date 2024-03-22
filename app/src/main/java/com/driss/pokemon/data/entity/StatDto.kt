package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.Stat
import com.driss.pokemon.domain.model.StatType

data class StatDto(
    val base_stat: Int,
    val effort: Int,
    val stat: StatTypeDto
)



internal fun List<StatDto>.toModel(): List<Stat> {
    return map {
        Stat(
            baseStat = it.base_stat,
            effort = it.effort,
            stat = it.stat.toModel()
        )
    }
}
