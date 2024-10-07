package com.JuanSalvadorGarcia.upaxtest.domain.usecase

import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonDetailsModel
import com.JuanSalvadorGarcia.upaxtest.domain.repository.PokemonRepository
import javax.inject.Inject

class GetFavoritePokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(id: Int): PokemonDetailsModel {
        return pokemonRepository.getFavorite(id)
    }

}