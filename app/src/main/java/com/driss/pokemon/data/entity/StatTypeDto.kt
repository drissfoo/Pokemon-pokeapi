package com.driss.pokemon.data.entity

import com.driss.pokemon.domain.model.StatType

data class StatTypeDto(
    val name: String,
    val url: String
)


fun StatTypeDto.toModel(): StatType = StatType(
    name = name,
    url = url
)