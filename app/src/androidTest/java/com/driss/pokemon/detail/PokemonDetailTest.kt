package com.driss.pokemon.detail

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
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
import com.driss.pokemon.presentation.detail.PokemonDetail
import com.driss.pokemon.presentation.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test


class PokemonDetailTest {
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
                    name = "Attack",
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
                slot = 2,
                type = TypeDto(
                    name = "normal",
                    url = ""
                )
            ),
        )
    )

    @Test
    fun test_pokemon_details_data_is_displayed_accordingly_on_the_UI() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            AppTheme {
                CompositionLocalProvider(LocalNavController provides navController) {
                    PokemonDetail(
                        pokemonDetails = venusaur.toModel(),
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("#3").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("Normal").assertIsDisplayed()
        composeTestRule.onNodeWithText("Attack").assertIsDisplayed()
        composeTestRule.onNodeWithText("23%").assertIsDisplayed()
    }
}