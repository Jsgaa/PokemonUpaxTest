package com.JuanSalvadorGarcia.upaxtest.data.response

import com.google.gson.annotations.SerializedName

data class PokemonDetailsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("sprites") val sprites: SpritesResponse,
    @SerializedName("types") val types: List<TypesResponse>
)
