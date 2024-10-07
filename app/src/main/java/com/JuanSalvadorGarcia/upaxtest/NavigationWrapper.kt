package com.JuanSalvadorGarcia.upaxtest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.JuanSalvadorGarcia.upaxtest.presentation.pokemonDetails.PokemonDetailsScreen
import com.JuanSalvadorGarcia.upaxtest.presentation.pokemonList.PokemonListScreen

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(navController = navHostController, startDestination = "list", modifier =  modifier) {
        composable("list") {
            PokemonListScreen(
                navHostController = navHostController
            )
        }

        composable("details?url={url}",
            arguments = listOf(
                navArgument(name = "url") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { backstackEntry ->
            PokemonDetailsScreen(url = backstackEntry.arguments?.getString("url") )
        }

    }

}