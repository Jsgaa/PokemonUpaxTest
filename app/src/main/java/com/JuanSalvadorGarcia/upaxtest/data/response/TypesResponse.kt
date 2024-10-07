package com.JuanSalvadorGarcia.upaxtest.data.response

import com.google.gson.annotations.SerializedName

data class TypesResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: TypeResponse
)
