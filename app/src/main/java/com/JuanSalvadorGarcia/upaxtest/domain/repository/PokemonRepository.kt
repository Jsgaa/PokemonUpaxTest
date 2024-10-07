package com.JuanSalvadorGarcia.upaxtest.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.JuanSalvadorGarcia.upaxtest.data.api.PokemonApiService
import com.JuanSalvadorGarcia.upaxtest.data.database.dao.PokemonDetailDao
import com.JuanSalvadorGarcia.upaxtest.data.database.entities.PokemonDetailEntity
import com.JuanSalvadorGarcia.upaxtest.data.di.NetworkHandler
import com.JuanSalvadorGarcia.upaxtest.data.response.PokemonDetailsResponse
import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonDetailsModel
import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonModel
import com.JuanSalvadorGarcia.upaxtest.domain.model.toDomain
import com.JuanSalvadorGarcia.upaxtest.domain.source.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiService: PokemonApiService,
    private val pokemonDetailDao: PokemonDetailDao
) {

    companion object {
        const val MAX_ITEMS = 25
        const val PREFETCH_ITEMS = 3
    }

    fun getAllPokemon(): Flow<PagingData<PokemonModel>> {
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = {
                PokemonPagingSource(apiService)
            }).flow
    }

    suspend fun getPokemonDetailsFromApi(url: String): PokemonDetailsModel {
        val response = apiService.getPokemonDetail(url)
        return response.toDomain()
    }

    suspend fun getPokemonDetailsFromDB(id: Int): PokemonDetailsModel {
        val response = pokemonDetailDao.getPokemon(id)
        return response.toDomain()
    }

    suspend fun insertPokemon(pokemon: PokemonDetailEntity){
        pokemonDetailDao.insertPokemon(pokemon)

    }

    suspend fun deletePokemon(id: Int) {
        pokemonDetailDao.deletePokemon(id)
    }

    suspend fun setFavorite(id: Int) {
        pokemonDetailDao.setFavorite(id)
    }

    suspend fun getFavorite(id: Int): PokemonDetailsModel {
        val response = pokemonDetailDao.getFavorite(id)
        return response.toDomain()
    }

    suspend fun removeFavorite(id: Int) {
        pokemonDetailDao.removeFavorite(id)
    }

}