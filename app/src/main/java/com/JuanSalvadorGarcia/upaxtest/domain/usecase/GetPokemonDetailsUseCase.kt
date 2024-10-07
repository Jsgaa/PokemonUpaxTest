package com.JuanSalvadorGarcia.upaxtest.domain.usecase

import androidx.lifecycle.asLiveData
import com.JuanSalvadorGarcia.upaxtest.data.database.entities.toDatabase
import com.JuanSalvadorGarcia.upaxtest.data.di.NetworkHandler
import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonDetailsModel
import com.JuanSalvadorGarcia.upaxtest.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val networkHandler: NetworkHandler
) {

    suspend operator fun invoke(id: String): PokemonDetailsModel {

        val idSplit = id.split("/")
        val idPokemon = idSplit[0].toInt()

        val isConnected = networkHandler.isConnected.asLiveData()
        if (isConnected.value == true) {
            val pokemonDetail = pokemonRepository.getPokemonDetailsFromApi(id)

            return if (pokemonDetail.name.isNotEmpty()) {
                pokemonRepository.insertPokemon(pokemonDetail.toDatabase())
                pokemonDetail
            } else {

                pokemonRepository.getPokemonDetailsFromDB(idPokemon)
            }
        } else {
            return pokemonRepository.getPokemonDetailsFromDB(idPokemon)
        }

    }
}