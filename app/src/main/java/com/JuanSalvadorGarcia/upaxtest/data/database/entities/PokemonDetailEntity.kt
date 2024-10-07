package com.JuanSalvadorGarcia.upaxtest.data.database.entities

import androidx.compose.ui.text.font.FontWeight
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonDetailsModel

@Entity(tableName = "pokemon_detail_table")
data class PokemonDetailEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "pokemonId") val pokemonId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "sprite") val sprite: String,
    @ColumnInfo(name = "types") val types: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean
)

fun PokemonDetailsModel.toDatabase() = PokemonDetailEntity(
    pokemonId = id,
    name = name,
    weight = weight,
    height = height,
    sprite = sprites,
    types = types,
    favorite = favorite
)