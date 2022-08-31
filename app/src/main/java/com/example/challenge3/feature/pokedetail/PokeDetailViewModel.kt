package com.example.challenge3.feature.pokedetail

import androidx.lifecycle.ViewModel
import com.example.challenge3.models.Pokemon

class PokeDetailViewModel: ViewModel() {
    lateinit var height: String
    lateinit var weight: String
    lateinit var link: String

    fun initialize(poke: Pokemon){
        height = "${poke.pokeDetail.height.toDouble() / 10} m"
        weight = "${poke.pokeDetail.weight.toDouble() / 10} kg"
        link = "https://bulbapedia.bulbagarden.net/wiki/${poke.name}"
    }
}