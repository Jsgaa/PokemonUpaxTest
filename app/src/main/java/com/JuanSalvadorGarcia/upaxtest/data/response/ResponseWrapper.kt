package com.JuanSalvadorGarcia.upaxtest.data.response

import com.google.gson.annotations.SerializedName

data class ResponseWrapper(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonResponse>
)
