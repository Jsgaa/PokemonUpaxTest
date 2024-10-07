package com.JuanSalvadorGarcia.upaxtest.data.response

import com.JuanSalvadorGarcia.upaxtest.domain.model.PokemonModel
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
) {
    fun toPresentation(): PokemonModel {
        return PokemonModel(
            name = name.orEmpty(),
            url = url.orEmpty()
        )
    }
}
