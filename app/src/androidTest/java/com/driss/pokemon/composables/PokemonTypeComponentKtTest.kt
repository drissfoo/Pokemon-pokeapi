package com.driss.pokemon.composables

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.driss.pokemon.presentation.navigation.LocalNavController
import com.driss.pokemon.data.entity.PokemonDto
import com.driss.pokemon.data.entity.PokemonTypeDto
import com.driss.pokemon.data.entity.SpritesDto
import com.driss.pokemon.data.entity.StatDto
import com.driss.pokemon.data.entity.StatTypeDto
import com.driss.pokemon.data.entity.TypeDto
import com.driss.pokemon.data.entity.toModel
import com.driss.pokemon.presentation.composables.PokemonTypeComponent
import com.driss.pokemon.presentation.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test

class PokemonTypeComponentKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    private val venusaur = PokemonDto(
        height = 0,
        id = 3,
        name = "venusaur",
        sprites = SpritesDto(""),
        stats = listOf(
            StatDto(
                baseStat = 23,
                effort = 0,
                stat = StatTypeDto(
                    name = "attack-defense",
                    url = ""
                )
            ),
            StatDto(
                baseStat = 89,
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
                slot = 1,
                type = TypeDto(
                    name = "normal",
                    url = ""
                )
            ),
            PokemonTypeDto(
                slot = 2,
                type = TypeDto(
                    name = "fire",
                    url = ""
                )
            ),
        )
    )

    @Test
    fun test_pokemon_stats_data_is_displayed_accordingly_on_the_UI() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            AppTheme {
                CompositionLocalProvider(LocalNavController provides navController) {
                    PokemonTypeComponent(
                        pokemonTypeSlots= venusaur.types.toModel()
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Fire").assertIsDisplayed()
        composeTestRule.onNodeWithText("Normal").assertIsDisplayed()
    }
}