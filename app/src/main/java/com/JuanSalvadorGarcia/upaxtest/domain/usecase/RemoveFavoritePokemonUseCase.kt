package com.JuanSalvadorGarcia.upaxtest.domain.usecase

import com.JuanSalvadorGarcia.upaxtest.domain.repository.PokemonRepository
import javax.inject.Inject

class RemoveFavoritePokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(id: Int) {
        pokemonRepository.removeFavorite(id)
    }
}