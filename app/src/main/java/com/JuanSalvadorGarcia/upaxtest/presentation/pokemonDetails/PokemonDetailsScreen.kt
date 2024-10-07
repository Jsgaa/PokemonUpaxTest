package com.JuanSalvadorGarcia.upaxtest.presentation.pokemonDetails

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.JuanSalvadorGarcia.upaxtest.R
import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonDetailsModel

@Composable
fun PokemonDetailsScreen(
    url: String?,
    pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
){
   Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.background),
            contentDescription = "forest",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

       pokemonDetailsViewModel.getPokemon(url.orEmpty())

       val pokemon: State<PokemonDetailsModel> = pokemonDetailsViewModel.pokemon.collectAsState()
       pokemonDetailsViewModel.getPokemonFavorite(null, url)

        PokemonData(pokemon.value, pokemonDetailsViewModel)
    }
}

@Composable
fun PokemonData(pokemon: PokemonDetailsModel, pokemonDetailsViewModel: PokemonDetailsViewModel) {
    val context = LocalContext.current
    val isFavorite = pokemonDetailsViewModel.isFavorite.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (isFavorite) {
                //Remover de favoritos
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "",
                    tint = Color.Red,
                    modifier = Modifier.clickable {
                        pokemonDetailsViewModel.addPokemonFavorite(pokemon.id)
                        Toast.makeText(context, "Removido de favoritos", Toast.LENGTH_SHORT).show()
                    })
            } else {
                //Agregar a favoritos
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "favorite",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        pokemonDetailsViewModel.addPokemonFavorite(pokemon.id)
                        Toast.makeText(context, "Agregado a favoritos", Toast.LENGTH_SHORT).show()
                    }
                )
            }
            AsyncImage(
                model = pokemon.sprites,
                modifier = Modifier
                    .size(200.dp),
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )

        }

        Column {
            Text(text = "Name: ${pokemon.name.replaceFirstChar { char -> char.titlecase() }}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column {
            Text(text = "Peso: ${pokemon.weight}",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column {
            Text(text = "Tamaño: ${pokemon.height}",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column {
            Text(text = "Tipo: ${pokemon.types}",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }

}