package com.JuanSalvadorGarcia.upaxtest.domain.model

import com.JuanSalvadorGarcia.upaxtest.data.database.entities.PokemonDetailEntity
import com.JuanSalvadorGarcia.upaxtest.data.response.PokemonDetailsResponse

data class PokemonDetailsModel(
    val id: Int = 0,
    val name: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val sprites: String = "",
    val types: String = "",
    var favorite: Boolean = false
)

fun PokemonDetailsResponse.toDomain() = PokemonDetailsModel(
    id,
    name,
    weight,
    height,
    sprites = sprites.frontDefault.orEmpty(),
    types = types.map { it.type.name }.toString(),
    favorite = false
)

fun PokemonDetailEntity.toDomain() = PokemonDetailsModel(
    id,
    name,
    weight,
    height,
    sprites = sprite,
    types = types,
    favorite = favorite
)

