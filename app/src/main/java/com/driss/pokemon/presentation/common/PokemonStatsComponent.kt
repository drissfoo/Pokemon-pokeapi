package com.driss.pokemon.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.driss.pokemon.domain.model.Stat
import com.driss.pokemon.domain.model.StatType
import com.driss.pokemon.ui.theme.Sizes
import com.driss.pokemon.util.extensions.formatCapitalize

@Composable
fun PokemonStatsComponent(
    stats: List<Stat>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        stats.forEach {
            PokemonStat(it)
        }
    }
}

@Composable
private fun PokemonStat(
    stat: Stat,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(horizontal = Sizes.M),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stat.stat.name.formatCapitalize(),
            modifier = Modifier.width(100.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.width(Sizes.XXS))
        CustomLinearProgressIndicator(
            modifier = Modifier
                .weight(1f)
                .height(15.dp),
            progress =
            stat.baseStat.toFloat().coerceAtLeast(0f).coerceAtMost(100f) / 100,
        )
        Spacer(modifier = Modifier.width(Sizes.XXS))
        Text(
            modifier = Modifier.width(40.dp),
            text = "${stat.baseStat}%"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonStatsComponentPreview() {
    PokemonStatsComponent(
        modifier = Modifier.fillMaxSize(),
        stats = listOf(
            Stat(baseStat = 12, effort = 0, stat = StatType("Defense", url = "")),
            Stat(baseStat = 27, effort = 0, stat = StatType("hp", url = "")),
            Stat(baseStat = 71, effort = 0, stat = StatType("Attack", url = "")),
        )
    )
}
