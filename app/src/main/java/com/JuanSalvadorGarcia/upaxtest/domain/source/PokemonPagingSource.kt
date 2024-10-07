package com.JuanSalvadorGarcia.upaxtest.domain.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.JuanSalvadorGarcia.upaxtest.data.api.PokemonApiService
import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonModel
import okio.IOException
import javax.inject.Inject

class PokemonPagingSource @Inject constructor( private val api: PokemonApiService): PagingSource<Int, PokemonModel>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonModel> {
        return try {

            val limit = 25

            val page = params.key ?: 0
            val response = api.getPokemons(page,limit)
            val pokemons = response.results
            val prevKey = if (response.previous != null) page - 25 else null
            val nextKey = if (response.next != null) page + 25 else null

            LoadResult.Page(data = pokemons.map { it.toPresentation()}, prevKey= prevKey, nextKey = nextKey)

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

}