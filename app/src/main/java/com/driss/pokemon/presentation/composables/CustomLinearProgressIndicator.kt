package com.driss.pokemon.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun CustomLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    progressColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    backgroundColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}