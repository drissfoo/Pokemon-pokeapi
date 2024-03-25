package com.driss.pokemon.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.driss.pokemon.presentation.detail.PokemonDetailScreen
import com.driss.pokemon.presentation.list.PokemonListScreen


val LocalNavController = compositionLocalOf<NavHostController> {
    error("Error")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = Screens.POKEMON_LIST_SCREEN) {
            composable(route = Screens.POKEMON_LIST_SCREEN) { PokemonListScreen() }

            composable(
                route = "${Screens.POKEMON_DETAIL_SCREEN}/{${Screens.POKEMON_DETAIL_SCREEN_NAME_ARG}}",
                arguments = listOf(navArgument(Screens.POKEMON_DETAIL_SCREEN_NAME_ARG) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                backStackEntry.arguments?.getString(Screens.POKEMON_DETAIL_SCREEN_NAME_ARG)
                    ?.let { PokemonDetailScreen(pokemonName = it) }
            }
        }

    }
}

object Screens {
    const val POKEMON_LIST_SCREEN = "list"
    const val POKEMON_DETAIL_SCREEN = "details"
    const val POKEMON_DETAIL_SCREEN_NAME_ARG = "name"
}