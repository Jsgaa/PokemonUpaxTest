package com.JuanSalvadorGarcia.upaxtest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.JuanSalvadorGarcia.upaxtest.data.database.entities.PokemonDetailEntity

@Dao
interface PokemonDetailDao {

    @Query("SELECT * FROM pokemon_detail_table WHERE pokemonId = :id")
    suspend fun getPokemon(id: Int): PokemonDetailEntity

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPokemon(pokemon: PokemonDetailEntity)

    @Query("DELETE FROM pokemon_detail_table WHERE pokemonId = :id")
    suspend fun deletePokemon(id: Int)

    @Query("SELECT * FROM pokemon_detail_table WHERE pokemonId = :id")
    suspend fun getFavorite(id: Int): PokemonDetailEntity

    @Query("UPDATE pokemon_detail_table SET favorite = 1 WHERE pokemonId = :id")
    suspend fun setFavorite(id: Int)

    @Query("UPDATE pokemon_detail_table SET favorite = 0 WHERE pokemonId = :id")
    suspend fun removeFavorite(id: Int)
}