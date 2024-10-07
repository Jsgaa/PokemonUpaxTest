package com.JuanSalvadorGarcia.upaxtest.data.api

import com.JuanSalvadorGarcia.upaxtest.data.response.PokemonDetailsResponse
import com.JuanSalvadorGarcia.upaxtest.data.response.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ResponseWrapper

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: String
    ): PokemonDetailsResponse
}