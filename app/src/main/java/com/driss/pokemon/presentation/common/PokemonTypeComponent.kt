package com.driss.pokemon.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.driss.pokemon.domain.model.PokemonType
import com.driss.pokemon.domain.model.Type
import com.driss.pokemon.ui.theme.Sizes
import com.driss.pokemon.util.extensions.capitalizeFirst
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonTypeComponent(
    modifier: Modifier = Modifier,
    pokemonTypeSlots: List<PokemonType>
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(Sizes.XXS),
        verticalArrangement = Arrangement.spacedBy(Sizes.XXS),
        modifier = modifier
            .padding(Sizes.XXS_2)
    ) {
        pokemonTypeSlots.forEach {
            FilterChip(
                selected = true,
                onClick = {},
                label = {
                    Text(text = it.type.name.capitalizeFirst())
                },
                colors = FilterChipDefaults.filterChipColors().copy(
                    containerColor = it.type.name.toColor(),
                    selectedContainerColor = it.type.name.toColor(),
                ),
            )
        }
    }
}

private fun String.toColor(): Color {
    val enumType = AllPokemonTypes.entries.first {
        it.name.lowercase(Locale.ROOT) == this.lowercase(Locale.ROOT)
    }
    return enumType.color
}

enum class AllPokemonTypes(val color: Color) {
    normal(Color(0xFFA8A877)),
    fire(Color(0xFFEF8030)),
    water(Color(0xFF6790F0)),
    electric(Color(0xFFF8CF30)),
    grass(Color(0xFF78C84F)),
    ice(Color(0xFF98D8D8)),
    fighting(Color(0xFFC03028)),
    poison(Color(0xFF9F409F)),
    ground(Color(0xFFE0C068)),
    flying(Color(0xFFA890F0)),
    psychic(Color(0xFFF85788)),
    bug(Color(0xFFA8B720)),
    rock(Color(0xFFB8A038)),
    ghost(Color(0xFF705898)),
    dragon(Color(0xFF7038F8)),
    dark(Color(0xFF705848)),
    steel(Color(0xFFB8B8D0)),
    fairy(Color(0xFFF0B5BC)),
    stellar(Color(0xFF34ACE7));
}

@Preview
@Composable
private fun PokemonTypeComponentPreview() {
    PokemonTypeComponent(
        pokemonTypeSlots = listOf(
            PokemonType(1, Type("normal", "")),
            PokemonType(1, Type("steel", "")),
            PokemonType(1, Type("psychic", "")),
            PokemonType(1, Type("ghost", "")),
            PokemonType(1, Type("water", "")),
            PokemonType(1, Type("fighting", "")),
        )
    )
}