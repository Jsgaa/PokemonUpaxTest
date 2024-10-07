package com.JuanSalvadorGarcia.upaxtest.presentation.pokemonDetails

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.PrimaryKey
import com.JuanSalvadorGarcia.upaxtest.data.di.NetworkHandler
import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonDetailsModel
import com.JuanSalvadorGarcia.upaxtest.domain.repository.PokemonRepository
import com.JuanSalvadorGarcia.upaxtest.domain.usecase.AddFavoritePokemonUseCase
import com.JuanSalvadorGarcia.upaxtest.domain.usecase.GetFavoritePokemonUseCase
import com.JuanSalvadorGarcia.upaxtest.domain.usecase.GetPokemonDetailsUseCase
import com.JuanSalvadorGarcia.upaxtest.domain.usecase.RemoveFavoritePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val addFavoritePokemonUseCase: AddFavoritePokemonUseCase,
    private val getFavoritePokemonUseCase: GetFavoritePokemonUseCase,
    private val removeFavoritePokemonUseCase: RemoveFavoritePokemonUseCase
): ViewModel() {

    private val _pokemon = MutableStateFlow<PokemonDetailsModel>(PokemonDetailsModel())
    val pokemon: StateFlow<PokemonDetailsModel> = _pokemon

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite


    fun getPokemon(url:String) {
        viewModelScope.launch {
            val urlSplit = url.split("https://pokeapi.co/api/v2/pokemon/")
            val id = urlSplit[1]
            val result = getPokemonDetailsUseCase(id)
            _pokemon.value = result
        }
    }

    fun addPokemonFavorite(id: Int) {
        getPokemonFavorite(id, null)
        viewModelScope.launch {

            if (isFavorite.value) {
                removeFavoritePokemonUseCase(id)
            } else {
                addFavoritePokemonUseCase(id)
            }
        }

    }

    fun getPokemonFavorite(id: Int?, url: String?) {
        viewModelScope.launch {
            if (url.isNullOrEmpty() && id != null) {
                val result = getFavoritePokemonUseCase(id)
                _isFavorite.value = result.favorite
            } else {
                val urlSplit = url?.split("https://pokeapi.co/api/v2/pokemon/")
                val id = urlSplit?.get(1)
                val result = id?.split("/")?.get(0)?.let { getFavoritePokemonUseCase(it.toInt()) }
                if (result != null) {
                    _isFavorite.value = result.favorite
                }
            }
        }
    }

}