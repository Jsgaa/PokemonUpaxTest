package com.JuanSalvadorGarcia.upaxtest.presentation.pokemonList

import androidx.lifecycle.ViewModel
import com.JuanSalvadorGarcia.upaxtest.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(pokemonRepository: PokemonRepository): ViewModel() {

    val pokemons = pokemonRepository.getAllPokemon()

}