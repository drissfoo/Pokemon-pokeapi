package com.driss.pokemon.list

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.driss.pokemon.LocalNavController
import com.driss.pokemon.data.entity.PokemonDto
import com.driss.pokemon.data.entity.PokemonTypeDto
import com.driss.pokemon.data.entity.SpritesDto
import com.driss.pokemon.data.entity.StatDto
import com.driss.pokemon.data.entity.StatTypeDto
import com.driss.pokemon.data.entity.TypeDto
import com.driss.pokemon.data.entity.toModel
import com.driss.pokemon.presentation.pokemonlist.PokemonCell
import com.driss.pokemon.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test


class PokemonCellTest {
    @get:Rule val composeTestRule = createComposeRule()

    private val venusaur = PokemonDto(
        height = 0,
        id = 3,
        name = "venusaur",
        sprites = SpritesDto(""),
        stats = listOf(
            StatDto(
                base_stat = 23,
                effort = 0,
                stat = StatTypeDto(
                    name = "Attack",
                    url = ""
                )
            ),
            StatDto(
                base_stat = 89,
                effort = 0,
                stat = StatTypeDto(
                    name = "hp",
                    url = ""
                )
            ),
        ),
        weight = 0,
        types = listOf(
            PokemonTypeDto(
                slot = 2,
                type = TypeDto(
                    name = "normal",
                    url = ""
                )
            ),
        )
    )

    @Test
    fun myTest() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            AppTheme {
                CompositionLocalProvider(LocalNavController provides navController) {
                    PokemonCell(
                        entry = venusaur.toModel()
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Venusaur").assertIsDisplayed()
        composeTestRule.onNodeWithText("#3").assertIsDisplayed()
    }
}