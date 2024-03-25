package com.driss.pokemon.presentation.composables

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
import com.driss.pokemon.presentation.ui.theme.Sizes
import com.driss.pokemon.util.extensions.capitalizeFirst
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonTypeComponent(
    pokemonTypeSlots: List<PokemonType>,
    modifier: Modifier = Modifier,
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

@Suppress("MagicNumber")
enum class AllPokemonTypes(val color: Color) {
    NORMAL(Color(0xFFA8A877)),
    FIRE(Color(0xFFEF8030)),
    WATER(Color(0xFF6790F0)),
    ELECTRIC(Color(0xFFF8CF30)),
    GRASS(Color(0xFF78C84F)),
    ICE(Color(0xFF98D8D8)),
    FIGHTING(Color(0xFFC03028)),
    POISON(Color(0xFF9F409F)),
    GROUND(Color(0xFFE0C068)),
    FLYING(Color(0xFFA890F0)),
    PSYCHIC(Color(0xFFF85788)),
    BUG(Color(0xFFA8B720)),
    ROCK(Color(0xFFB8A038)),
    GHOST(Color(0xFF705898)),
    DRAGON(Color(0xFF7038F8)),
    DARK(Color(0xFF705848)),
    STEEL(Color(0xFFB8B8D0)),
    FAIRY(Color(0xFFF0B5BC)),
    STELLAR(Color(0xFF34ACE7));
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