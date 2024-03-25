package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.Stat
import com.google.gson.annotations.SerializedName

data class StatDto(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatTypeDto
)


internal fun List<StatDto>.toModel(): List<Stat> {
    return map {
        Stat(
            baseStat = it.baseStat,
            effort = it.effort,
            stat = it.stat.toModel()
        )
    }
}
