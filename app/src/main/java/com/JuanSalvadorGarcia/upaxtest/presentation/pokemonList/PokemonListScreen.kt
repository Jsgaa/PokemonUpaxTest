package com.JuanSalvadorGarcia.upaxtest.presentation.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.JuanSalvadorGarcia.upaxtest.R
import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonModel

@Composable
fun PokemonListScreen(
    pokemonListViewModel: PokemonListViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val pokemons = pokemonListViewModel.pokemons.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.background),
            contentDescription = "forest",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
    }

    when{
        pokemons.loadState.refresh is LoadState.Loading && pokemons.itemCount == 0 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp),
                    color = Color.White)
            }
        }
        pokemons.loadState.refresh is LoadState.NotLoading && pokemons.itemCount == 0 -> {
            Text(text = "No se encontró información", color = Color.Red, fontSize = 40.sp)
        }
        pokemons.loadState.hasError -> {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Red), contentAlignment = Alignment.Center) {
                Text(text = "Ha ocurrido un error")
            }
        }

        else -> {

            PokemonList(pokemons = pokemons, navigateToPokemonDetails = navHostController)
            
            if (pokemons.loadState.append is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(64.dp),
                        color = Color.White)
                }
            }

        }

    }
}

@Composable
fun PokemonList(pokemons: LazyPagingItems<PokemonModel>, navigateToPokemonDetails: NavHostController) {

    LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        items(pokemons.itemCount) {
            pokemons[it]?.let { pokemonModel ->
                ItemList(pokemonModel = pokemonModel, navigateToPokemonDetails)
            }
        }
    }

}

@Composable
fun ItemList(pokemonModel: PokemonModel, navigateToPokemonDetails: NavHostController) {

    Box(
        modifier = Modifier
            .padding(24.dp)
            .clip(CircleShape)
            .width(200.dp)
            .height(200.dp)
            .background(Color.White)
            .clickable { navigateToPokemonDetails.navigate("details?url=${pokemonModel.url}") },
        contentAlignment = Alignment.Center
    ) {
         val imageRequest = ImageRequest.Builder(LocalContext.current)
             .data(pokemonModel.url)
             .build()

        val painter = rememberAsyncImagePainter(model = imageRequest)

        when (painter.state) {
            is AsyncImagePainter.State.Success -> {
                AsyncImage(
                    model = imageRequest,
                    contentDescription = "character image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.placeholder)
                )
            }
            else -> {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxSize()
                    .border(2.dp, Color.Green, shape = CircleShape)
                    .background(Color.White),
                    contentAlignment = Alignment.Center) {
                    Text(text = pokemonModel.name[0].uppercase(), textAlign = TextAlign.Center, fontSize = 100.sp, color = Color.Black)
                }
            }
        }
    }
    Text(text = pokemonModel.name.replaceFirstChar { char -> char.titlecase() }, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)

}

