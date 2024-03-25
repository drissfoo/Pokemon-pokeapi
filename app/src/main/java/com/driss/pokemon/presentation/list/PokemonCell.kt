package com.driss.pokemon.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.driss.pokemon.presentation.navigation.LocalNavController
import com.driss.pokemon.presentation.navigation.Screens
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.presentation.ui.theme.Sizes
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import java.util.Locale

@Composable
fun PokemonCell(
    entry: Pokemon,
    modifier: Modifier = Modifier,
) {

    val roundedCornerSize = Sizes.S
    val navController = LocalNavController.current

    Card(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(roundedCornerSize)
            )
            .shadow(2.dp, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(roundedCornerSize))
            .clickable {
                navController.navigate(
                    "${Screens.POKEMON_DETAIL_SCREEN}/${entry.name}"
                )
            }
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Spacer(modifier = Modifier.height(Sizes.XS))
            Text(
                text = "#${entry.id}",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Sizes.XXS)
            )
            CoilImage(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                imageModel = { entry.frontSprite },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.Center,
                    contentDescription = entry.name,
                ),
            )
            Text(
                text = entry.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Sizes.XXS)
            )
            Spacer(modifier = Modifier.height(Sizes.XS))
        }
    }
}