package com.example.challenge3.models

import com.google.gson.annotations.SerializedName

data class PokemonURL(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<Pokemon?>? = null
)